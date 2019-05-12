package com.les.ecommerce.dao.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.cliente.Cupom;
import com.les.ecommerce.repository.cliente.CupomRepository;

@Component
public class CupomDAO extends AbstractDAO {

	@Autowired
	private CupomRepository repository;
	
	@Override
	public void salvar(IEntidade entidade) {
		repository.save(noCast(entidade));
	}

	@Override
	public void alterar(IEntidade entidade) {
		Cupom cupom = (Cupom) entidade;
		if(cupom.getId() > 0) {
			repository.save(cupom);
		}
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		Cupom cupom = (Cupom) entidade;
		List<Predicate<Cupom>> predicates = new ArrayList<>();
		
		if(cupom.getId() > 0)
			predicates.add(c -> c.getId() == cupom.getId());
		
		if(!StringHelper.isNullOrEmpty(cupom.getCodigo()))
			predicates.add(c -> c.getCodigo().equals(cupom.getCodigo()));
		
		
		Predicate<Cupom> compositePredicate = predicates.stream().reduce(c -> true, Predicate::and);
		
		return repository.findAll(Sort.by("id")).stream().filter(compositePredicate)
				.collect(Collectors.toList());
	}

	@Override
	public void deletar(IEntidade entidade) {
		repository.delete(noCast(entidade));
	}

}
