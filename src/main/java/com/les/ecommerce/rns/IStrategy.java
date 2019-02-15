package com.les.ecommerce.rns;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;

@Component
public interface IStrategy {

	public String processar(EntidadeDominio entidade);
}
