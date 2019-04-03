package com.les.ecommerce.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.autenticacao.User;

@Controller
@RequestMapping("/conta")
public class ContaController extends BaseController{

	@RequestMapping("/login")
	public String login(User user) {
		return "views/conta/login";
	}
	
	@RequestMapping("/registrar")
	public String registro(User user) {
		return "views/conta/registrar";
	}
	
	
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String registro(User user, RedirectAttributes redirectAttributes,Model model) {
		
		if(user.getPassword().contains("admin")) {
			user.setRoleName("ADMIN");
		}else {
			user.setRoleName("USER");
		}
		
		Resultado resultado = this.commands.get(SALVAR).execute(user);
		if(resultado.getMsg() == null) {
			resultado.setMsg("Conta criada com sucesso fazer login no sistema");
			redirectAttributes.addFlashAttribute("resultado", resultado);
			return "redirect:/";
		}
		
		model.addAttribute("resultado", resultado);
		
		return "views/conta/registrar";
	}
	
}
