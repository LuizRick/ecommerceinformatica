package com.les.ecommerce.rns.pedido;

import java.time.temporal.ChronoUnit;

import com.les.ecommerce.application.venda.Relatorio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.rns.IStrategy;

public class ValidarIntervaloDataRelatorio implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Relatorio relatorio = (Relatorio) entidade;
		
		if(relatorio.getDataInicial() == null || relatorio.getDataFinal() == null) {
			return null;
		}
		
		
		if(relatorio.getDataInicial().isAfter(relatorio.getDataFinal())) {
			return "A data inicial não pode ser apos a data final";
		}
		
		
		if(relatorio.getDataInicial().until(relatorio.getDataFinal(), ChronoUnit.MONTHS) < 2) {
			return "A diferença da data inicial para a final deve ser de no minimo 2 meses";
		}
		
		return null;
	}

}
