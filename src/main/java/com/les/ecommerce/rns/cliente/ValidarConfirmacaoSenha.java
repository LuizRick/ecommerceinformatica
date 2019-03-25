package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarConfirmacaoSenha implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getUsuario() == null || !cliente.getUsuario().getPassword().equals(cliente.getUsuario().getConfirmaPassword())) {
			return "a senha de confirmação deve ser identica a senha digitada";
		}
		
		return null;
	}

}
