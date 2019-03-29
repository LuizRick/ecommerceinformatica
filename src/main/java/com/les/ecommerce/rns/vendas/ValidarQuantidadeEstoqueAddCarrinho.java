package com.les.ecommerce.rns.vendas;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.les.ecommerce.SpringContext;
import com.les.ecommerce.dao.produto.ProdutoDAO;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarQuantidadeEstoqueAddCarrinho implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Carrinho carrinho = (Carrinho) entidade;
		StringBuilder sb  = new StringBuilder();
		ApplicationContext context = SpringContext.getAppContext();
		ProdutoDAO produtoDAO = (ProdutoDAO) context.getBean(ProdutoDAO.class);
		for(ItemCarrinho item : carrinho.getItens()) {
			List<IEntidade> entidades = produtoDAO.consultar(item.getProduto());
			entidades.stream().forEach( enti -> {
				Produto produto = (Produto) enti;
				if(produto.getId() == item.getProduto().getId() &&
						produto.getEstoque() < item.getQuantidade()) {
					sb.append("E não tem quantidade no estoque para compra deste item (estoque " + produto.getEstoque() + ")<br/>");
				}
			});
		}
		return null;
	}

}
