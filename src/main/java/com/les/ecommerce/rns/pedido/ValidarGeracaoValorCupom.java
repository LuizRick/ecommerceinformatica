package com.les.ecommerce.rns.pedido;

import java.text.DecimalFormat;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.rns.IStrategy;

public class ValidarGeracaoValorCupom implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		if(pedido.getCartao().size() <= 0 && pedido.getCupom().size() > 0) {
			Double totalCompra = 0D, totalCupom = 0D;

			for(ItemPedido item : pedido.getItens()) {
				totalCompra += item.getProduto().getValorVenda();
			}
			
			if(pedido.getValorFrete() != null) {
				totalCompra += pedido.getValorFrete();
			}else {
				return "O valor do frete não pode ser vazio";
			}
			
			for(Cupom c : pedido.getCupom()) {
				totalCupom += c.getValor();
			}
			
			Double vintePorcento = totalCompra * 0.3D;
			Double totalMenosTrinta = totalCompra - vintePorcento;
			DecimalFormat formatDecimal = new DecimalFormat("####0.00");
			
			if(totalCupom > totalCompra && Math.abs(totalCompra - totalCupom) > totalMenosTrinta) {
				return "O valor total pago em cupons ultrapassa o valor de 30% " + formatDecimal.format(totalMenosTrinta) +" permitido para compra";
			}
		}
		
		return null;
	}

}
