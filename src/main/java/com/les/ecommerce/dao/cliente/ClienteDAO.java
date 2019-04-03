package com.les.ecommerce.dao.cliente;

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
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.repository.autenticacao.RoleRepository;
import com.les.ecommerce.repository.cliente.ClienteRepository;

@Component
public class ClienteDAO extends AbstractDAO {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository role;
	
	
	@Override
	public void salvar(IEntidade entidade) {
		Role userRole = role.findByRole("USER");
		Cliente cliente = (Cliente) entidade;
		cliente.getUsuario().setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		cliente.getUsuario().setPassword(bCryptPasswordEncoder.encode(cliente.getUsuario().getPassword()));
		repository.save(noCast(cliente));
	}

	@Override
	public void alterar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		Role userRole = role.findByRole("USER");
		cliente.getUsuario().setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		cliente.getUsuario().setPassword(bCryptPasswordEncoder.encode(cliente.getUsuario().getPassword()));
		if(cliente.getId() > 0) {
			repository.save(noCast(cliente));
		}
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		List<Predicate<Cliente>> predicates = new ArrayList<Predicate<Cliente>>();
		
		if(cliente.getId() > 0)
			predicates.add(c -> c.getId() == cliente.getId());
		if(!StringHelper.isNullOrEmpty(cliente.getNome()))
			predicates.add(c -> c.getNome().equalsIgnoreCase(cliente.getNome()));
		
		if(!StringHelper.isNullOrEmpty(cliente.getDataNascimento()))
			predicates.add(c -> c.getDataNascimento().isEqual(cliente.getDataNascimento()));
		
		if(!StringHelper.isNullOrEmpty(cliente.getCpf()))
			predicates.add(c -> c.getCpf().equals(cliente.getCpf()));
		
		if(!StringHelper.isNullOrEmpty(cliente.getUsuario()) && !StringHelper.isNullOrEmpty(cliente.getUsuario().getEmail()))
			predicates.add(c -> c.getUsuario().getEmail().equals(cliente.getUsuario().getEmail()));
		
		
		Predicate<Cliente> compositedPredicate = predicates.stream().reduce(c -> true, Predicate::and);
		return repository.findAll(Sort.by("id")).stream().filter(compositedPredicate).collect(Collectors.toList());
	}

	@Override
	public void deletar(IEntidade entidade) {
		Cliente cliente = (Cliente) entidade;
		if(cliente.getId() > 0 && cliente.getUsuario().getActive() == 1) {
			repository.delete(noCast(entidade));
		}else if(cliente.getId() > 0 && cliente.getUsuario().getActive() == 2) {
			repository.save(cliente);
		}
	}

}
