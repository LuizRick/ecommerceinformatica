package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarDadosObrigatorioPedido implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		StringBuilder sb = new StringBuilder();
		
		if(pedido.getEnderecoEntrega() == null || pedido.getEnderecoEntrega().getCep() == null) {
			sb.append("E necessario selecionar um endereço de entrega valido");
		}
		
		
		if(pedido.getCartao() == null) {
			sb.append("E necessario preencher o valor de um dos cartões para finalizar sua compra</br>");
		}
		
		if(pedido.getCliente() == null) {
			sb.append("E necesario ter um cliente logado para fazer compras");
		}
		
		if(pedido.getItens() == null) {
			sb.append("E necessario ter itens no seu pedido");
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
