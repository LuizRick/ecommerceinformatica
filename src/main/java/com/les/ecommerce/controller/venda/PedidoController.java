package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.les.ecommerce.controller.BaseController;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends BaseController {

	
	@RequestMapping(value="/carrinho/fechamento", method=RequestMethod.POST)
	public String fecharPedido() {
		
		return "views/pedidos/fechamento";
	}
}
