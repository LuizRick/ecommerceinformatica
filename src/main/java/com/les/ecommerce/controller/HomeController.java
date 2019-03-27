package com.les.ecommerce.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.RequireJS;

import com.les.ecommerce.application.pagination.AbstractPagination;
import com.les.ecommerce.application.pagination.Pagination;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.produto.Produto;

@Controller
public class HomeController extends BaseController {

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

	@RequestMapping(value = "/home/carrinho/add", method = RequestMethod.POST)
	public String addCarrinho(ItemCarrinho item,Model model) throws CloneNotSupportedException {
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		if (carrinho == null) {
			carrinho = new Carrinho();
			session.setAttribute("carrinho", carrinho);
		}
		
		for(ItemCarrinho i : carrinho.getItens()) {
			if(i.getProduto().getId() == item.getProduto().getId()) {
				item.setProduto(i.getProduto());
				carrinho.getItens().remove(i);
				carrinho.getItens().add(item);
			}
		}
		Produto entidade = new Produto();
		entidade.setId(item.getProduto().getId());
		Resultado resultado = this.commands.get(CONSULTAR).execute(entidade);
		item.setProduto((Produto)resultado.getEntidades().get(0));
		carrinho.getItens().add(item);
		session.setAttribute("carrinho", carrinho);
		return "redirect:/carrinho/listar";
	}

}
