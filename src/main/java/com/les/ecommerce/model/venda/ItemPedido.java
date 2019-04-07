package com.les.ecommerce.model.venda;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.produto.Produto;

@Entity
public class ItemPedido extends EntidadeDominio implements IEntidade {

	@OneToOne
	private Produto produto;
	
	private Double quantidade;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
