package com.les.ecommerce.controller.venda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;
import com.les.ecommerce.model.wrappers.RetornoEstoqueForm;

@Controller
public class PedidosAdminController extends BaseController {

	
	@RequestMapping("/admin/cliente/pedidos")
	public String listar(Pedido pedido, Authentication auth, Model model) {
		Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
		List<IEntidade> pedidos = resultado.getEntidades();
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("resultado", resultado);
		return "views/pedidos/adminListarPedidos";
	}
	
	@RequestMapping("/admin/pedidos/visualizar/{id}")
	public String visualizar(Pedido pedido,Model model) {
		Resultado resultado = this.commands.get(CONSULTAR).execute(pedido);
		pedido = (Pedido) resultado.getEntidades().get(0);
		model.addAttribute("pedido", pedido);
		model.addAttribute("resultado", resultado);
		return "/views/pedidos/adminVisualizar";
	}
	
	@RequestMapping(value="/admin/pedidos/alterarStatus", method=RequestMethod.POST)
	public String verificar(Pedido pedido, RetornoEstoqueForm retorno,RedirectAttributes redAttr, Authentication auth) {
		boolean hasAuthAdmin = this.hasRole("ADMIN", auth.getAuthorities());
		if(hasAuthAdmin) {
			Pedido pedidoConsulta = new Pedido();
			pedidoConsulta.setId(pedido.getId());
			Resultado resultado = this.commands.get(CONSULTAR).execute(pedidoConsulta);
			pedidoConsulta = (Pedido) resultado.getEntidades().get(0);
			pedidoConsulta.setStatusPedido(pedido.getStatusPedido());
			
			if(pedido.getStatusPedido() == StatusPedido.TROCADO) {
				retorno.setPedido(pedidoConsulta);
				Resultado resultadoRetorno = this.commands.get(SALVAR).execute(retorno);
				if(resultadoRetorno.getMsg() != null) {
					redAttr.addFlashAttribute("resultado", resultadoRetorno);
					return "redirect:/admin/pedidos/visualizar/" + pedido.getId();
				}
				
				for(ItemPedido item : retorno.getItensRetorno()) {
					resultadoRetorno = this.commands.get(CONSULTAR).execute(item.getProduto());
					
				}
				
			}
			
			resultado = this.commands.get(ALTERAR).execute(pedidoConsulta);
			if(resultado.getMsg() == null) {
				resultado.setMsg("Produto foi alterado com sucesso");
			}
			redAttr.addFlashAttribute("resultado", resultado);
		}
		return "redirect:/admin/cliente/pedidos";
	}
}
