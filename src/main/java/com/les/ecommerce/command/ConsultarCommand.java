package com.les.ecommerce.command;

import org.springframework.stereotype.Component;

import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.IEntidade;

@Component
public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(IEntidade entidade) {
		return fachada.consultar(entidade);
	}
}
