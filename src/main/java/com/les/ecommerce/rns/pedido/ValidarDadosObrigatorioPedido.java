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
			sb.append("<div>E necessario selecionar um endereço de entrega valido</div>");
		}
		
		if(pedido.getCliente() == null) {
			sb.append("<div>E necesario ter um cliente logado para fazer compras</div>");
		}
		
		if(pedido.getItens() == null || pedido.getItens().size() <= 0) {
			sb.append("<div>E necessario ter itens no seu pedido</div>");
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
