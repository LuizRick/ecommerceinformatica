package com.les.ecommerce.rns.produto;

import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarInativacaoProduto implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Produto produto = (Produto) entidade;
		
		if("SALVARTASK".equals(produto.getAction())) {
			return null;
		}
		
		if(produto.getId() == 0) {
			return "E necessario um produto para inativar";
		}
		
		if(produto.getCategoriaInativacao() == null) {
			return "E necessario uma categoria de inativação";
		}
		
		if(StringHelper.isNullOrEmpty(produto.getJustificativaInativacao())) {
			return "E necessario uma justificativa inativação";
		}
		
		return null;
	}

}
