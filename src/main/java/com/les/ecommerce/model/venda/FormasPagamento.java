package com.les.ecommerce.model.venda;

import java.util.Set;

import com.les.ecommerce.model.EntidadeAplicacao;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.INotPersistente;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cupom;

public class FormasPagamento extends EntidadeAplicacao implements INotPersistente, IEntidade {

	private Set<Cartao> cartao;
	
	private Set<Cupom> cupons;

	public Set<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(Set<Cartao> cartao) {
		this.cartao = cartao;
	}

	public Set<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(Set<Cupom> cupons) {
		this.cupons = cupons;
	}

	
}
