package com.les.ecommerce.command;

import org.springframework.stereotype.Component;

import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.EntidadeDominio;

@Component
public class AlterarCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.alterar(entidade);
	}

}
