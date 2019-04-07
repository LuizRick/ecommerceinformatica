package com.les.ecommerce.controller.venda;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.les.ecommerce.controller.BaseController;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.produto.Produto;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController extends BaseController {


	@RequestMapping("/listar")
	public String listar(Model model) {
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		Double total = 0D;
		int totalProdutos  = 0;
		if(carrinho != null && carrinho.getItens() != null) {
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCarrinho(ItemCarrinho item,Model model , RedirectAttributes redirect) throws CloneNotSupportedException {
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
		Resultado resultadoSalvar = this.commands.get(SALVAR).execute(carrinho);
		if(resultadoSalvar.getMsg() != null) {
			carrinho.getItens().remove(item);
			redirect.addFlashAttribute("resultado", resultadoSalvar);
			return "redirect:/";
		}
		session.setAttribute("carrinho", carrinho);
		return "redirect:/carrinho/listar";
	}
	
	@RequestMapping(value="/remover/{index}")
	public String removeItem(@PathVariable("index") int index) {
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		ItemCarrinho item = null;
		if(carrinho != null) {
			for(int i = 0; i < carrinho.getItens().size();i++) {
				if(i == index)
					item = (ItemCarrinho) carrinho.getItens().toArray()[i];
			}
			
			if(item != null) {
				carrinho.getItens().remove(item);
			}
		}
		return "redirect:/carrinho/listar";
	}
}
