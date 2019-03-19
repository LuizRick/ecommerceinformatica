package com.les.ecommerce.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.cliente.Cliente;
import com.les.ecommerce.repository.cliente.ClienteRepository;

@Component
public class ClienteDAO extends AbstractDAO {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		repository.save(noCast(entidade));
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		if(entidade.getId() > 0) {
			repository.save(noCast(entidade));
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		List<Predicate<Cliente>> predicates = new ArrayList<Predicate<Cliente>>();
		if(cliente.getId() > 0)
			predicates.add(c -> c.getId() == cliente.getId());
		
		Predicate<Cliente> compositedPredicate = predicates.stream().reduce(c -> true, Predicate::and);
		return repository.findAll(Sort.by("id")).stream().filter(compositedPredicate).collect(Collectors.toList());
	}

	@Override
	public void deletar(EntidadeDominio entidade) {
		if(entidade.getId() > 0) {
			repository.delete(noCast(entidade));
		}
	}

}
