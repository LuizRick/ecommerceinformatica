package com.les.ecommerce.model.cliente;

public enum TipoTelefone {

	RESIDENCIAL("RESIDENCIAL");
	
	private String value;
	
	TipoTelefone(String value){
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
