package com.les.ecommerce.model.aplication;

import com.les.ecommerce.model.EntidadeAplicacao;
import com.les.ecommerce.model.produto.Produto;

public class ItemCarrinho extends EntidadeAplicacao {
	
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
