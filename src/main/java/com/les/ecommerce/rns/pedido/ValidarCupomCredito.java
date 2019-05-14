package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarCupomCredito implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;

		if(pedido.getCupom().size() > 0 && pedido.getCartao().size() > 0) {
			Double totalCupom = 0D,
				   totalCompra = 0D;
			
			for(Cupom c : pedido.getCupom()) {
				totalCupom += c.getValor();
			}
			
			
			Double totalCartao = 0D;
			
			for(Cartao c : pedido.getCartao()) {
				totalCartao += c.getValor();
			}
			
			for(ItemPedido item : pedido.getItens()) {
				totalCompra += item.getProduto().getValorVenda();
			}
			
			if(pedido.getValorFrete() != null) {
				totalCompra += pedido.getValorFrete();
			}else {
				return "O valor do frete não pode ser vazio";
			}
			
			
			Double vintePorcento = totalCompra * 0.1D;
			Double totalMenosVinte = totalCompra - vintePorcento;
			for(Cartao cartao : pedido.getCartao()){
				if(cartao.getValor() <= vintePorcento  &&  totalCupom > vintePorcento ) {
					return "O valor minimo para cada cartão e de 10% do valor da compra ou " + totalMenosVinte + " para cada cartão";
				}
			}
		}
		return null;
	}

}
