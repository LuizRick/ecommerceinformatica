package com.les.ecommerce.rns.pedido;

import java.time.LocalDate;
import java.util.Set;

import com.les.ecommerce.helpers.pedido.CartaoCreditoUtils;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.venda.FormasPagamento;
import com.les.ecommerce.rns.IStrategy;

public class ValidarCartaoCredito implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		FormasPagamento pedido = (FormasPagamento) entidade;
		Set<Cartao> cartoes = pedido.getCartao();
		StringBuilder sb = new StringBuilder();
		for(Cartao c : cartoes) {
			if(!CartaoCreditoUtils.validCCNumber(c.getNumero())) {
				sb.append("Cartão de credito " + c.getNumero() + " tem numero inválido");
			}
			
			
			if(c.getValidade() == null || c.getValidade().isBefore(LocalDate.now())) {
				sb.append("O cartão e invalido");
			}
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		return null;
	}

}
