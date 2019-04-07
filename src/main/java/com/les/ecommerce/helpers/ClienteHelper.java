package com.les.ecommerce.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.les.ecommerce.command.ConsultarCommand;
import com.les.ecommerce.facade.Resultado;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.model.cliente.Cliente;

@Component
public class ClienteHelper {

	@Autowired
	private ConsultarCommand command;
	
	public Cliente getClienteAuth(Authentication auth) {
		Cliente cliente = new Cliente();
		User user = new User();
		user.setEmail(auth.getName());
		cliente.setUsuario(user);
		Resultado resultado = command.execute(cliente);
		if(resultado.getMsg() != null) {
			return null;
		}
		
		return (Cliente) resultado.getEntidades().get(0);
	}
}
