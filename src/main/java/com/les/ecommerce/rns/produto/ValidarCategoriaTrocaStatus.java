package com.les.ecommerce.rns.produto;

import java.util.Arrays;
import java.util.List;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.CategoriaInativacao;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarCategoriaTrocaStatus implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		
		if(produto.getStatus() == true && !this.isCategoriaAtivacao(produto)) {
			return "Para ativação deve selecionar uma categoria de ativação valida";
		}
		
		if(produto.getStatus() == false && !this.isCategoriaInativacao(produto)){
			return "Para inativação deve selecionar uma categoria de inativação valida";
		}
		return null;
	}
	
	
	private boolean isCategoriaAtivacao(Produto produto) {
		CategoriaInativacao[] ativacao = new CategoriaInativacao[]{
				CategoriaInativacao.RETORNOMERCADO
		};
		List<CategoriaInativacao> categorias = Arrays.asList(ativacao);
		return categorias.contains(produto.getCategoriaInativacao());
	}
	
	private boolean isCategoriaInativacao(Produto produto) {
		CategoriaInativacao[] inativacao = new CategoriaInativacao[]{
				CategoriaInativacao.FORAMERCADO
		};
		List<CategoriaInativacao> categorias = Arrays.asList(inativacao);
		return categorias.contains(produto.getCategoriaInativacao());
	}

}
