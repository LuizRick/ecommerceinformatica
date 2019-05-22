package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.application.venda.Relatorio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDataInicialFinalRelatorio implements IStrategy{

	
	@Override
	public String processar(IEntidade entidade) {
		Relatorio relatorio = (Relatorio) entidade;
		
		if(relatorio.getDataInicial() != null && relatorio.getDataFinal() == null) {
			return "A data final deve ser preenchida quando a data inicial estiver preenchida";
		}
		
		
		if(relatorio.getDataFinal() != null && relatorio.getDataInicial() == null) {
			return "A data inicial precisa ser preenchida quando a data final for preenchida";
		}
			
		
		return null;
	}
	
	
}
