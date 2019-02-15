package com.les.ecommerce.dao;

public abstract class AbstractDAO implements IDAO {	
	@SuppressWarnings("unchecked")
	protected <T> T noCast(Object entidade) {
		return (T) entidade;
	}
}
