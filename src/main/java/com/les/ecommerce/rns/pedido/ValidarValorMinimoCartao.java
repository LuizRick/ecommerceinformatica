package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarValorMinimoCartao implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		for(Cartao cartao : pedido.getCartao()){
			if(cartao.getValor() <= 10.0D) {
				return "O valor minimo para cada cartão e de R$ 10.00";
			}
		}
		return null;
	}

}
