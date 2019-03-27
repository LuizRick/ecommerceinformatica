package com.les.ecommerce.rns.produto;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarProdutoAtivo implements IStrategy{

	
	@Override
	public String processar(IEntidade entidade) {
		Produto produto = (Produto) entidade;
		if(produto.getStatus() != null && produto.getStatus() == false && produto.getCategoriaInativacao() == null) {
			return "O produto deve estar ativo para ser salvo";
		}
		return null;
	}
}
