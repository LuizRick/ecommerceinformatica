package com.les.ecommerce.application.interceptors;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.les.ecommerce.dao.cliente.ClienteDAO;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.Role;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.repository.autenticacao.UserRepository;

@Component
public class UserAuthModelHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && request.getAttribute("conta") == null) {
			User user = repository.findByEmail(auth.getName());
			if(user != null && containsRole("USER", user.getRoles())) {
				Cliente cliente = new Cliente();
				cliente.setUsuario(user);
				List<IEntidade> entidade = clienteDAO.consultar(cliente);
				
				if(entidade != null && entidade.size() > 0) {
					cliente = (Cliente) entidade.get(0);
					request.setAttribute("conta", cliente);	
				}
			}
		}
		
		return true;
	}
	
	private boolean containsRole(String role,Set<Role> roles) {
		boolean hasRules = false;
		for(Role r : roles) {
			hasRules = r.getRole().equals(role);
		}
		
		return hasRules;
	}

}
