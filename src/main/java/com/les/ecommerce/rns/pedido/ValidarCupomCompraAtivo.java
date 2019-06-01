package com.les.ecommerce.rns.pedido;

import java.util.Optional;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.model.venda.FormasPagamento;
import com.les.ecommerce.rns.IStrategy;

public class ValidarCupomCompraAtivo implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		FormasPagamento pedido = (FormasPagamento) entidade;
		StringBuilder sb = new StringBuilder();
		
		Optional<Cupom> cupom =  pedido.getCupons()
				.stream()
				.filter(c -> !c.isAtivo())
				.findAny();
		
		if(cupom.isPresent()) {
			sb.append("Foi encontrado um cupom que não esta mais valido " + cupom.get().getCodigo() + 
					"! Por favor remover e selecionar outro <br/>");
		}
		
		
		if(sb.length() > 0) {
			return sb.toString();
		}
		
		return null;
	}

}
