/**
 * 
 */
package com.les.ecommerce.rns.cliente;

import org.springframework.context.ApplicationContext;

import com.les.ecommerce.SpringContext;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.repository.autenticacao.UserRepository;
import com.les.ecommerce.rns.IStrategy;

/**
 * @author luizl
 *
 */
public class ValidarEmailUnicoCliente implements IStrategy {

	/* (non-Javadoc)
	 * @see com.les.ecommerce.rns.IStrategy#processar(com.les.ecommerce.model.IEntidade)
	 */
	@Override
	public String processar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		User userEntity = cliente.getUsuario();
		ApplicationContext context = SpringContext.getAppContext();
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user = userRepository.findByEmail(userEntity.getEmail());
		if(user != null) {
			return "Email já esta sendo utilizado, por favor tente outro!!!";
		}
		return null;
	}

}
