package com.les.ecommerce.dao.usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.autenticacao.Role;
import com.les.ecommerce.model.autenticacao.User;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.repository.autenticacao.RoleRepository;
import com.les.ecommerce.repository.autenticacao.UserRepository;
import com.les.ecommerce.repository.cliente.ClienteRepository;

@Component
public class UserDAO extends AbstractDAO {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RoleRepository role;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public void salvar(IEntidade entidade) {
		User user = (User) entidade;
		Role userRole = role.findByRole(user.getRoleName());
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		User newUser = repository.save(user);
		if(user.getRoleName().contains("USER")) {
			Cliente cliente = new Cliente();
			cliente.setUsuario(newUser);
			clienteRepository.save(cliente);
		}
	}

	@Override
	public void alterar(IEntidade entidade) {
		User user = (User) entidade;
		Role userRole = role.findByRole(user.getRoleName());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		if(user.getId() > 0) {
			repository.save(user);
		}
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		User user = (User) entidade;
		List<Predicate<User>> predicates = new ArrayList<Predicate<User>>();
		if(user.getId() > 0)
			predicates.add(u -> u.getId() == user.getId());
		
		if(!StringHelper.isNullOrEmpty(user.getEmail()))
			predicates.add(u -> u.getEmail().equals(user.getEmail()));
		
		if(!StringHelper.isNullOrEmpty(user.getPassword()))
			predicates.add(u -> u.getEmail().equals(user.getPassword()));
		
		
		Predicate<User> compositedPredicate = predicates.stream().reduce(c -> true, Predicate::and);
		return repository.findAll(Sort.by("id")).stream().filter(compositedPredicate).collect(Collectors.toList());
	}

	@Override
	public void deletar(IEntidade entidade) {
		
	}

}
