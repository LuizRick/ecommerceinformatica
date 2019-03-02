package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.les.ecommerce.controller.BaseController;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends BaseController {


	@RequestMapping("/listar")
	public String listar() {
		return "views/carrinho/listar";
	}
}
