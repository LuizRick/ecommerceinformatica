package com.les.ecommerce.rns.produto;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarProdutoAtivo implements IStrategy{

	
	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		if(produto.getStatus() != null && produto.getStatus() == false) {
			return "O produto deve estar ativo para ser salvo";
		}
		return null;
	}
}
