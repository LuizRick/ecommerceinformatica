package com.les.ecommerce.rns.user;

import org.springframework.context.ApplicationContext;

import com.les.ecommerce.SpringContext;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.repository.autenticacao.UserRepository;
import com.les.ecommerce.rns.IStrategy;

public class ValidarEmailUnico implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		User userEntity = (User) entidade;
		ApplicationContext context = SpringContext.getAppContext();
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user = userRepository.findByEmail(userEntity.getEmail());
		if(user != null) {
			return "Email já esta sendo utilizado, por favor tente outro!!!";
		}
		return null;
	}

}
