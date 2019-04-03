package com.les.ecommerce.rns.user;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.rns.IStrategy;

public class ValidarSenhaForteUser implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!+=])(?=\\S+$).{8,}";
		User user = (User) entidade;
		
		if(!user.getPassword().matches(pattern)) {
			return "A senha digitada pelo cliente e invalida.";
		}
		
		return null;
	}

}
