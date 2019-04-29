package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarOperadoraCartaoCredito implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		if(pedido.getStatusPedido() == StatusPedido.APROVADO) {
			return null;
		}
		
		
		if(pedido.getStatusPedido() != StatusPedido.PROCESSAMENTO) {
			return null;
		}
		
		
		
		return null;
	}

}
