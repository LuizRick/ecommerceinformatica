package com.les.ecommerce.dao.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.model.venda.Pedido;
import com.les.ecommerce.repository.pedido.PedidoRepository;

@Component
public class PedidoDAO extends AbstractDAO {

	@Autowired
	private PedidoRepository repository;
	
	@Override
	public void salvar(IEntidade entidade) {
		repository.save(noCast(entidade));
	}

	@Override
	public void alterar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		if(pedido.getId() > 0) {
			this.repository.save(noCast(entidade));
		}
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		Pedido pedido = (Pedido) entidade;
		List<Predicate<Pedido>> predicates = new ArrayList<Predicate<Pedido>>();
		if(pedido.getId() > 0)
			predicates.add(p -> p.getId() == pedido.getId());
		Predicate<Pedido> compositedPredicate = predicates.stream().reduce(c -> true, Predicate::and);
		return repository.findAll(Sort.by("id")).stream().filter(compositedPredicate).collect(Collectors.toList());
	}

	@Override
	public void deletar(IEntidade entidade) {
		repository.delete(noCast(entidade));
	}

}
