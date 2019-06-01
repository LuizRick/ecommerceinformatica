package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarValorMinimoCartao implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		Double totalCompra  = 0.0D;
		if(pedido.getCupom().size() <= 0 && pedido.getCartao().size() > 0) {
			for(ItemPedido item : pedido.getItens()) {
				totalCompra += item.getProduto().getValorVenda();
			}
			
			if(pedido.getValorFrete() != null) {
				totalCompra += pedido.getValorFrete();
			}
			
			Double vintePorcento = totalCompra * 0.1D;
			for(Cartao cartao : pedido.getCartao()){
				if(cartao.getValor() <= vintePorcento) {
					return "O valor minimo para cada cartão e de 10% do valor da compra ou " + vintePorcento + " para cada cartão";
				}
			}
		}
		
		
		return null;
	}

}
