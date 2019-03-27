package com.les.ecommerce.rns;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.IEntidade;

@Component
public interface IStrategy {

	public String processar(IEntidade entidade);
}
