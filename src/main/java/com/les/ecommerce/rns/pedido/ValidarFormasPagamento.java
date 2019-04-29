package com.les.ecommerce.rns.pedido;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarFormasPagamento implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		Double totalCompra = 0.0D;
		
		for(ItemPedido item : pedido.getItens()) {
			totalCompra += item.getQuantidade() * item.getProduto().getValorVenda();
		}
		
		Double total = ( (pedido.getValorFrete() != null) ? pedido.getValorFrete() : 0.0D) + totalCompra;
		
		
		for(Cartao cartao : pedido.getCartao()) {
			total -= cartao.getValor();
		}
		
		for(Cupom cupom : pedido.getCupom()) {
			total -= cupom.getValor();
		}
		
		
		if(Math.round(total) > 0.00D) {
			return String.format("O valor da compra não foi totalmente satisfeito faltando R$%.2f para ser pago", total);
		}
		
		return null;
	}

}
