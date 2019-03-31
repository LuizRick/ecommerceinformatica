package com.les.ecommerce.command;

import org.springframework.stereotype.Component;

import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.IEntidade;

@Component
public interface ICommand {
	public Resultado execute(IEntidade entidade);
}