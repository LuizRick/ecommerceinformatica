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
			for(IEntidade enti: entidades) {
				Produto produto = (Produto) enti;
				if(produto.getId() == item.getProduto().getId() && item.getQuantidade() != null &&
						item.getQuantidade() > produto.getEstoque()) {
					String ms = String.format("Quantidade indisponivel no estoque para o produto: %s; estoque : %.0f <br/>",
							produto.getDescricao(),produto.getEstoque());
					sb.append(ms);
				}
			}
		}
		
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		return null;
	}

}
