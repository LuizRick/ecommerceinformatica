package com.les.ecommerce.controller.venda;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;

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
	
	@RequestMapping("/admin/pedidos/alterarStatus")
	public String verificar(Pedido pedido, RedirectAttributes redAttr, Authentication auth) {
		boolean hasAuthAdmin = this.hasRole("ADMIN", auth.getAuthorities());
		if(hasAuthAdmin) {
			Pedido pedidoConsulta = new Pedido();
			pedidoConsulta.setId(pedido.getId());
			Resultado resultado = this.commands.get(CONSULTAR).execute(pedidoConsulta);
			pedidoConsulta = (Pedido) resultado.getEntidades().get(0);
			pedidoConsulta.setStatusPedido(pedido.getStatusPedido());
			resultado = this.commands.get(ALTERAR).execute(pedidoConsulta);
			if(resultado.getMsg() == null) {
				resultado.setMsg("Produto foi alterado com sucesso");
			}
			redAttr.addFlashAttribute("resultado", resultado);
		}
		return "redirect:/admin/cliente/pedidos";
	}
}
