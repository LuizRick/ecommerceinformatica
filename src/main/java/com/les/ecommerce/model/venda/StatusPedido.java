package com.les.ecommerce.model.venda;

public enum StatusPedido {

	PROCESSAMENTO("EM PROCESSAMENTO"),
	TRANSITO("EM TRANSITO"),
	ENTREGUE("ENTREGUE"),
	AUTORIZADOTROCA("TROCA AUTORIZADA"),
	EMTROCA("EM TROCA");
	
	private String name;
	
	private StatusPedido(String name) {
		this.name = name;
	}
	
	public String value() {
		return this.name;
	}
}
