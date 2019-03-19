package com.les.ecommerce.model.cliente;

public enum Genero {

	MASCULINO("M"),
	FEMININO("F");
	
	private String value;
	
	
	private Genero(String value) {
		this.value = value;
	}
	
	
	public String value() {
		return this.value;
	}
	
	
}
