package com.les.ecommerce.controller.cliente;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.CartaoCliente;
import com.les.ecommerce.model.cliente.Cliente;

@Controller
@RequestMapping("/admin/cartao")
public class CartaoController extends BaseController {

	
	@RequestMapping(value="/cadastro" , method=RequestMethod.GET)
	public String cadastro(Cartao cartao, Authentication auth) {
		
		return "/views/cartoes/cadastrar";
	}
	
	
	@RequestMapping(value="/cadastro" , method=RequestMethod.POST)
	public String cadastro(CartaoCliente cartao, Authentication auth, RedirectAttributes redAttr) {
		Cliente cliente =  (Cliente) this.session.getAttribute("cliente");
		if(cliente == null) {
			return "redirect:" + this.request.getHeader("Referer");
		}
		
		cliente.getCartoes().add(cartao);
		if(cartao.isSalvarPerfil()) {
			Resultado resultado = this.commands.get(ALTERAR).execute(cliente);
			if(resultado.getMsg() == null) {
				resultado.setMsg("Cartão foi adicionado e salvo a conta do cliente com sucesso!!!");
				redAttr.addFlashAttribute("resultado", resultado);
			}
			this.session.setAttribute("cliente", cliente);
			return "redirect:/carrinho/listar?callback=redirecionaPedido";
		}else {
			this.session.setAttribute("cliente", cliente);
			return "redirect:/carrinho/listar?callback=redirecionaPedido";
		}
	}
	
}
