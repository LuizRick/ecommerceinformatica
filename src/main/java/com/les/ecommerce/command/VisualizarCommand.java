package com.les.ecommerce.command;

import org.springframework.stereotype.Component;

import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.EntidadeDominio;

@Component
public class VisualizarCommand extends AbstractCommand{

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.visualizar(entidade);
	}
}
