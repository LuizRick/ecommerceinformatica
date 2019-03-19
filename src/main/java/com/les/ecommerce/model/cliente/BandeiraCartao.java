package com.les.ecommerce.model.cliente;

public enum BandeiraCartao {

	
	MASTERCARD("MASTER CARD"),
	DINNERS("DINNER CLUB"),
	VISA("VISA"),
	ELO("ELO"),
	HIPERCARD("HIPERCARD"),
	AMERICANEXPRESS("AMERICAN EXPRESS");
	
	private String value;
	
	
	private BandeiraCartao(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
