package com.les.ecommerce.rns.user;

import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.rns.IStrategy;

public class ValidarConfirmacaoSenha implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		User user = (User) entidade;
		
		if(!user.getPassword().equals(user.getConfirmaPassword())) {
			return "a senha de confirmação deve ser identica a senha digitada";
		}
		
		return null;
	}

}
