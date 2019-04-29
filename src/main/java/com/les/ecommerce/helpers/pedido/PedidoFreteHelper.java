package com.les.ecommerce.helpers.pedido;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.les.ecommerce.model.venda.CartaoPedido;
import com.les.ecommerce.model.venda.Compra;
import com.les.ecommerce.model.venda.EnderecoPedido;
import com.les.ecommerce.model.venda.ItemPedido;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.model.venda.StatusPedido;


@Component
public class PedidoFreteHelper {

	public Double calcularFreteByCep(String cep) {
		String lastDigits = cep.substring(cep.length() - 3);
		Double CepDigits = 777D;
		Double f = Double.parseDouble(lastDigits);
		return Math.abs((f - CepDigits)) * Math.random();
	}
	
	public Pedido mapperPedido(Compra compra) {
		Pedido pedido = new Pedido();
		pedido.setCartao(new HashSet<>());
		pedido.setCliente(compra.getCliente());
		pedido.setItens(new HashSet<>());
		pedido.setCupom(new HashSet<>());
		pedido.setStatusPedido(StatusPedido.PROCESSAMENTO);
		pedido.setValorFrete(compra.getValorFrete());
		if(compra.getEnderecoEntrega() != null) {
			pedido.setEnderecoEntrega(EnderecoPedido.newInstace(compra.getCliente().getEnderecos().get((int)compra.getEnderecoEntrega().getId())));	
		}
		
		if(compra.getCartao() != null) {
			compra.getCartao().forEach(c -> {
				compra.getCliente().getCartoes().forEach(cc -> {
					if(cc.getId() == c.getId() && c.getValor() != null) {
						cc.setValor(c.getValor());
						pedido.getCartao().add(CartaoPedido.newInstance(cc));
					}
				});
			});
		}
		
		compra.getCarrinho().getItens().forEach(item -> {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(item.getProduto());
			itemPedido.setQuantidade(item.getQuantidade());
			pedido.getItens().add(itemPedido);
		});
		
		if(compra.getCupom() != null) {
			compra.getCupom().forEach(cupom -> {
				pedido.getCupom().add(cupom);
			});
		}
		return pedido;
	}
}
