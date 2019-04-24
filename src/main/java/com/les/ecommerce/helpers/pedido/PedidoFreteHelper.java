package com.les.ecommerce.helpers.pedido;

import org.springframework.stereotype.Component;


@Component
public class PedidoFreteHelper {

	public Double calcularFreteByCep(String cep) {
		String lastDigits = cep.substring(cep.length() - 3);
		Double CepDigits = 777D;
		Double f = Double.parseDouble(lastDigits);
		return Math.abs((f - CepDigits)) * (Math.random() * 20);
	}
}
