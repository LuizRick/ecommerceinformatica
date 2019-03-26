package com.les.ecommerce.model.aplication;

import java.util.HashSet;
import java.util.Set;

import com.les.ecommerce.model.EntidadeAplicacao;


public class Carrinho extends EntidadeAplicacao {

	private Set<ItemCarrinho> itens;
	
	
	public Carrinho() {
		itens = new HashSet<ItemCarrinho>();
	}

	public Set<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(Set<ItemCarrinho> itens) {
		this.itens = itens;
	}
}
