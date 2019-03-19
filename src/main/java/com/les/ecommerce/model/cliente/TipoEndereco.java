package com.les.ecommerce.model.cliente;

public enum TipoEndereco {

	CASA("CASA"),
	APARTAMENTO("APARTAMENTO");
	
	private String value;
	
	private TipoEndereco(String value) {
		this.value = value;
	}
	
	
	public String value() {
		return this.value;
	}
}
