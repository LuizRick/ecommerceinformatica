package com.les.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

import com.les.ecommerce.application.pagination.AbstractPagination;
import com.les.ecommerce.application.pagination.Pagination;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.produto.Produto;

@Controller
public class HomeController extends BaseController {

	
	@Autowired
	private SimpMessagingTemplate messageTemplate;
	
	@ResponseBody
	@RequestMapping(value = "/webjarsjs", produces = "application/javascript")
	public String webjarjs() {
		return RequireJS.getSetupJavaScript("/webjars/");
	}

	@RequestMapping("/")
	public String home(Model model) {
		Produto produto = new Produto();
		produto.setStatus(true);
		AbstractPagination pagination = new Pagination();
		pagination.setPageRequest(PageRequest.of(0, Integer.MAX_VALUE));
		Resultado resultado = this.commands.get(CONSULTAR).execute(produto);
		model.addAttribute("produtos", resultado.getEntidades());
		model.addAttribute("item", new ItemCarrinho());
		return "views/home";
	}
	
	
	@MessageMapping("/session-chanel")
	public void sessionMessages(SimpMessageHeaderAccessor header) throws Exception{
		this.messageTemplate.convertAndSend("");
	}
	
	@RequestMapping("/sair")
	public String sair() {
		session.invalidate();
		return "redirect:/";
	}
}
