package com.les.ecommerce.model.produto;

public enum CategoriaInativacao {

	
	FORAMERCADO("FORA DE MERCADO"),
	RETORNOMERCADO("RETORNO DE MERCADO");
	
	
	private String categoria;
	
	
	CategoriaInativacao(String categoria){
		this.categoria = categoria;
	}
	
	
	public String value() {
		return this.categoria;
	}
}
