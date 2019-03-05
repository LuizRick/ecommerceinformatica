package com.les.ecommerce.dao;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDAO implements IDAO {	
	@SuppressWarnings("unchecked")
	protected <T> T noCast(Object entidade) {
		return (T) entidade;
	}
}
