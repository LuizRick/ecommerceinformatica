package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends BaseController {


	@RequestMapping("/listar")
	public String listar(Model model) {
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		Double total = 0D;
		int totalProdutos  = 0;
		if(carrinho != null) {
			for(ItemCarrinho item : carrinho.getItens()) {
				total += item.getProduto().getValorVenda() * item.getQuantidade();
				totalProdutos +=  Math.round(item.getQuantidade());
			}
		}
		model.addAttribute("carrinho", carrinho);
		model.addAttribute("total", total);
		model.addAttribute("totalProdutos", totalProdutos);
		return "views/carrinho/listar";
	}
}
