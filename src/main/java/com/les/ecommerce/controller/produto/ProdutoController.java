package com.les.ecommerce.controller.produto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.les.ecommerce.controller.BaseController;

@Controller
@RequestMapping("/admin/produtos/")
public class ProdutoController extends BaseController {

	@RequestMapping(value="/cadastro", method=RequestMethod.GET)
	public String cadastro() {
		return "/views/produto/cadastrar";
	}
	
	
	@RequestMapping(value="/cadastro/{id}", method=RequestMethod.GET)
	public String visualizar(@PathVariable("id") Integer id) {
		return "views/produto/cadastrar";
	}
}
