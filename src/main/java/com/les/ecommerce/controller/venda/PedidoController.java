package com.les.ecommerce.controller.venda;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.helpers.ClienteHelper;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.venda.Pedido;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends BaseController {

	@Autowired
	private ClienteHelper clienteHelper;
	
	@RequestMapping(value="/carrinho/fechamento", method=RequestMethod.POST)
	public String fecharPedido(Pedido pedido, Model model,Authentication auth) {
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
		pedido.setCupom(new HashSet<>());
		pedido.setCliente(cliente);
		model.addAttribute("carrinho", carrinho);
		model.addAttribute("total", total);
		model.addAttribute("totalPagar", total);
		model.addAttribute("totalProdutos", totalProdutos);
		return "views/pedidos/fechamento";
	}
	
	@RequestMapping(value="/carrinho/finalizar")
	public String finalizar(Pedido pedido,Authentication auth) {
		Cliente cliente;
		if(this.session.getAttribute("cliente") == null) {
			cliente = clienteHelper.getClienteAuth(auth);
		}else {
			cliente = (Cliente) this.session.getAttribute("cliente");
		}
		
		return "views/pedidos/finalizar";
	}
	
	
	@RequestMapping(value="/frete/calcula")
	public @ResponseBody Double valorFrete(String cep) {
		
		return 10.5D;
	}
}
