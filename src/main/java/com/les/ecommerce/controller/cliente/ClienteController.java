package com.les.ecommerce.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.model.cliente.Cliente;

@Controller
public class ClienteController extends BaseController {

	
	@RequestMapping(path="/admin/cliente/cadastro")
	public String cadastrar(Cliente cliente) {
		
		return "views/cliente/cadastrar";
	}
	
	@RequestMapping(value="/admin/cliente/salvar",method=RequestMethod.POST)
	public String salvar(Cliente cliente) {
		
		return "views/cliente/cadastrar";
	}
}
