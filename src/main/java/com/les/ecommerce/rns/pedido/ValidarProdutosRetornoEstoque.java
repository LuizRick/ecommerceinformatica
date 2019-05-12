package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.wrappers.RetornoEstoqueForm;
import com.les.ecommerce.rns.IStrategy;

public class ValidarProdutosRetornoEstoque implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		RetornoEstoqueForm retorno = (RetornoEstoqueForm) entidade;

		final StringBuilder sb = new StringBuilder();

		retorno.getItensRetorno().forEach(i -> {
			
			if(i.getQuantidade() != null) {
				retorno.getPedido().getItens().forEach(p -> {
					if (p.getProduto().getId() == i.getProduto().getId() && i.getQuantidade() > p.getQuantidade()) {
						sb.append(String.format(
								"A quantidade de retorno não pode ser maior que a quantidade retornada no pedido para o produto %s",
								p.getProduto().getDescricao()));
					}
				});
			}
			
			
			if(i.getQuantidade() != null && i.getQuantidade() < 0) {
				sb.append(String.format("A quantidade de retorno não pode ser menor que zero para o produto %s", i.getProduto().getId()));
			}
		});
		
		if(sb.length() > 0) {
			return sb.toString();
		}

		return null;
	}

}
