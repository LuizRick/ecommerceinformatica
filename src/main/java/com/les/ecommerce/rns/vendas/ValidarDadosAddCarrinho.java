package com.les.ecommerce.rns.vendas;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.aplication.Carrinho;
import com.les.ecommerce.model.aplication.ItemCarrinho;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosAddCarrinho implements IStrategy{

	
	@Override
	public String processar(IEntidade entidade) {
		Carrinho carrinho = (Carrinho) entidade;
		
		if(carrinho.getItens() != null && carrinho.getItens().size() <= 0) {
			return "e necessário adicionar itens ao carrinho com quantidade valida";
		}
		
		for(ItemCarrinho item : carrinho.getItens()) {
			if(item.getQuantidade() == null || item.getQuantidade() <= 0) {
				return "Entrar com uma quantidade correta";
			}
			
			if(item.getProduto() == null || item.getProduto().getId() <= 0) {
				return "Deve selecionar um produto valido para adicionar ao carrinho";
			}
		}
		
		return null;
	}
}
