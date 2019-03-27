package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.les.ecommerce.controller.BaseController;

@Controller
@RequestMapping("/pedido")
public class PedidoController extends BaseController {

	
	@RequestMapping(value="/carrinho/fechamento")
	public String fecharPedido() {
		
		return "views/pedidos/fechamento";
	}
}
