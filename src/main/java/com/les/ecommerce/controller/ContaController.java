package com.les.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@RequestMapping("/login")
	public String login() {
		return "views/conta/login";
	}
	
}
