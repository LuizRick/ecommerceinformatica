package com.les.ecommerce.controller.cliente;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.cliente.EnderecoCliente;


@Controller
@RequestMapping("/admin/endereco")
public class EnderecoController extends BaseController {

	
	@RequestMapping(value="/cadastro" , method=RequestMethod.GET)
	public String cadastro(Endereco endereco, Authentication auth) {
		
		return "/views/endereco/cadastrar";
	}
	
	
	@RequestMapping(value="/cadastro" , method=RequestMethod.POST)
	public String cadastro(EnderecoCliente endereco, Authentication auth, RedirectAttributes redAttr) {
		Cliente cliente =  (Cliente) this.session.getAttribute("cliente");
		if(cliente == null) {
			return "redirect:/carrinho/listar?callback=redirecionaPedido";
		}
		
		cliente.getEnderecos().add(endereco);
		if(endereco.getSalvarPerfil()) {
			Resultado resultado = this.commands.get(ALTERAR).execute(cliente);
			if(resultado.getMsg() == null) {
				resultado.setMsg("Endereço foi adicionado e salvo a conta do cliente com sucesso!!!");
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
