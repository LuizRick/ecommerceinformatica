package com.les.ecommerce.controller.venda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Objects;
import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.helpers.ClienteHelper;
import com.les.ecommerce.helpers.pedido.PedidoFreteHelper;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.venda.Compra;
import com.les.ecommerce.model.venda.Pedido;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends BaseController {

	@Autowired
	private ClienteHelper clienteHelper;
	
	@Autowired
	private PedidoFreteHelper pedidoHelper;
	
	@RequestMapping(value="/carrinho/fechamento", method=RequestMethod.POST)
	public String fecharPedido(Compra compra, Model model,Authentication auth) {
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		Double total = 0D;
		int totalProdutos  = 0;
		if(carrinho != null && carrinho.getItens() != null) {
			for(ItemCarrinho item : carrinho.getItens()) {
				total += item.getProduto().getValorVenda() * item.getQuantidade();
				totalProdutos +=  Math.round(item.getQuantidade());
			}
		}
		Cliente cliente;
		if(this.session.getAttribute("cliente") == null) {
			cliente = clienteHelper.getClienteAuth(auth);
			this.session.setAttribute("cliente", cliente);
		}else {
			cliente = (Cliente) this.session.getAttribute("cliente");
		}
		compra.setCupom(new ArrayList<>());
		compra.setCartao(new ArrayList<>());
		cliente.getCartoes().forEach(c -> compra.getCartao().add(c));
		compra.setCliente(cliente);
		model.addAttribute("carrinho", carrinho);
		model.addAttribute("total", total);
		model.addAttribute("totalPagar", total);
		model.addAttribute("totalProdutos", totalProdutos);
		model.addAttribute("valorFrete", compra.getValorFrete());
		return "views/pedidos/fechamento";
	}
	
	@RequestMapping(value="/carrinho/finalizar", method=RequestMethod.POST)
	public String finalizar(Compra compra,Authentication auth, RedirectAttributes redirAttr,Model model) {
		Cliente cliente;
		if(this.session.getAttribute("cliente") == null) {
			cliente = clienteHelper.getClienteAuth(auth);
		}else {
			cliente = (Cliente) this.session.getAttribute("cliente");
		}
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		compra.setCarrinho(carrinho);
		compra.setCliente(cliente);
		
		Pedido mapPedido = pedidoHelper.mapperPedido(compra);
		mapPedido.setCreated(LocalDateTime.now());
		Resultado resultado = this.commands.get(this.SALVAR).execute(mapPedido);
		
		if(resultado.getMsg() == null) {
			resultado.setMsg("O pedido foi finalizado com sucesso");
			redirAttr.addFlashAttribute("resultado", resultado);
			session.removeAttribute("carrinho");
			return "redirect:/pedido/cliente/listar";
		}
		
		Double total = 0D;
		int totalProdutos  = 0;
		if(carrinho != null && carrinho.getItens() != null) {
			for(ItemCarrinho item : carrinho.getItens()) {
				total += item.getProduto().getValorVenda() * item.getQuantidade();
				totalProdutos +=  Math.round(item.getQuantidade());
			}
		}
		
		if(compra.getCupom() != null && cliente.getCupons() != null) {
			List<Cupom> cupons = new ArrayList<>();
			compra.getCupom().forEach(c -> {
				cliente.getCupons().forEach(clienteCup -> {
					if(clienteCup != null) {
						if(c.getId() == clienteCup.getId())
							cupons.add(clienteCup);
					}
				});
			});
			compra.setCupom(cupons);
		} else {
			compra.setCupom(new ArrayList<>());
		}
		compra.setCartao(new ArrayList<>());
		cliente.getCartoes().forEach(c -> compra.getCartao().add(c));
		model.addAttribute("carrinho", carrinho);
		model.addAttribute("total", total);
		model.addAttribute("totalPagar", total);
		model.addAttribute("totalProdutos", totalProdutos);
		model.addAttribute("resultado", resultado);
		model.addAttribute("valorFrete",compra.getValorFrete());
		return "views/pedidos/fechamento";
	}
	
	
	@RequestMapping(value="/frete/calcula")
	public @ResponseBody Double valorFrete(Integer index) {
		Cliente cliente = (Cliente) this.session.getAttribute("cliente");
		Endereco endereco = cliente.getEnderecos().get(index);
		return pedidoHelper.calcularFreteByCep(endereco.getCep());
	}
	
	@RequestMapping(value="/cliente/listar")
	public String listar(Pedido pedido, Authentication auth, Model model) {
		Cliente cliente = (Cliente) this.session.getAttribute("cliente");
		pedido.setCliente(cliente);
		Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
		List<IEntidade> pedidos = resultado.getEntidades();
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("resultado", resultado);
		return "views/pedidos/listar";
	}
	
	@RequestMapping(value="/cliente/visualizar/{id}")
	public String visualizar(Pedido pedido, Authentication auth, Model model) {
		Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
		pedido = (Pedido) resultado.getEntidades().get(0);
		model.addAttribute("pedido", pedido);
		model.addAttribute("resultado", resultado);
		return "views/pedidos/visualizar";
	}
	
	@RequestMapping(value="/cliente/alterarStatus")
	public String alterarStatus(Pedido pedido, RedirectAttributes redAttr, Authentication auth) {
		boolean hasAuthAdmin = this.hasRole("USER", auth.getAuthorities());
		if(hasAuthAdmin) {
			Pedido pedidoConsulta = new Pedido();
			pedidoConsulta.setId(pedido.getId());
			Resultado resultado = this.commands.get(CONSULTAR).execute(pedidoConsulta);
			pedidoConsulta = (Pedido) resultado.getEntidades().get(0);
			pedidoConsulta.setStatusPedido(pedido.getStatusPedido());
			resultado = this.commands.get(ALTERAR).execute(pedidoConsulta);
			if(resultado.getMsg() == null) {
				resultado.setMsg("A troca foi solicitada com sucesso");
			}
			redAttr.addFlashAttribute("resultado", resultado);
		}
		return "redirect:/pedido/cliente/listar";
	}
	
	@RequestMapping(value="/cupom/get")
	@ResponseBody
	public Optional<Cupom> getCupom(String cupom, Authentication auth) {
		Cliente cliente = clienteHelper.getClienteAuth(auth);
		return cliente.getCupons().stream()
				.filter(c -> java.util.Objects.nonNull(c))
				.filter( c -> cupom.equalsIgnoreCase(c.getCodigo()))
				.findFirst();
	}
}
