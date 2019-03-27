package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarCartaoObrigatorioNovoCliente implements IStrategy{
	
	@Override
	public String processar(IEntidade entidade) {
		Cliente cliente  = (Cliente) entidade;
		if(cliente.getCartoes() == null || cliente.getCartoes().size() <= 0) {
			return "é necessario cadastrar ao menos um cartão";
		}
		return null;
	}
}