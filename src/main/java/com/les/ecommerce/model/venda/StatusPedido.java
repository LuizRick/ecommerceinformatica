package com.les.ecommerce.model.venda;

public enum StatusPedido {

	PROCESSAMENTO("EM PROCESSAMENTO"),
	TRANSITO("EM TRANSITO"),
	ENTREGUE("ENTREGUE"),
	TROCADO("TROCADO"),
	EMTROCA("EM TROCA"),
	APROVADO("APROVADO"),
	REPROVADO("REPROVADO"),
	TROCA_AUTORIZADA("TROCA AUTORIZADA");
	
	private String name;
	
	private StatusPedido(String name) {
		this.name = name;
	}
	
	public String value() {
		return this.name;
	}
}
