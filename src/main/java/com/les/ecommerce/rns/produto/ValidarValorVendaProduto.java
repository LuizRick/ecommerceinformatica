package com.les.ecommerce.rns.produto;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.rns.IStrategy;

public class ValidarValorVendaProduto implements IStrategy{

	
	@Override
	public String processar(IEntidade entidade) {
		Produto produto = (Produto) entidade;
		
		if(produto.getValorVenda() == null || produto.getGrupoPrecificacao() == null || produto.getValorCusto() == null) {
			return "Os valores para venda são de preencimento obrigatorio";
		}
		
		 if(produto.getValorVenda() <  calcularMargeLucro(produto)  +  produto.getValorCusto())
			 return String
					 .format("O valor de venda esta abaixo do valor da margem %.2f", 
							 calcularMargeLucro(produto) + produto.getValorCusto());
		
		return null;
	}
	
	
	private Double calcularMargeLucro(Produto produto) {
		
		Double margem = (produto.getGrupoPrecificacao().getPorcentagem() * produto.getValorCusto()) / 100;
		
		return margem;
	}
}
