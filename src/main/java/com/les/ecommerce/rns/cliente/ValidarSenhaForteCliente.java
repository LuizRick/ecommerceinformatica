package com.les.ecommerce.rns.cliente;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.rns.IStrategy;

public class ValidarSenhaForteCliente implements IStrategy{

	
	@Override
	public String processar(IEntidade entidade) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!+=])(?=\\S+$).{8,}";
		Cliente cliente = (Cliente) entidade;
		
		if(cliente.getUsuario() == null || !cliente.getUsuario().getPassword().matches(pattern)) {
			return "A senha digitada pelo cliente e invalida, sua senha precisa de no minimo 8 caracteres" + 
					"e ser composta de caracteres alphanumericos e caracteres especiais e nao pode conter espacos";
		}
		
		return null;
	}
}
