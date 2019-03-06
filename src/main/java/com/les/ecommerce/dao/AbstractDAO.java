package com.les.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;

@Component
public abstract class AbstractDAO implements IDAO {	
	
	private JpaRepository<? extends EntidadeDominio, Long> repository;
	
	
	public AbstractDAO() {
		
	}
	
	protected void setRepository(JpaRepository<? extends EntidadeDominio, Long> repository) {
		
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T noCast(Object entidade) {
		return (T) entidade;
	}
}
