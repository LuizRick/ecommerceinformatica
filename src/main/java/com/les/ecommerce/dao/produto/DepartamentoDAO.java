package com.les.ecommerce.dao.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.IEntidade;
import com.les.ecommerce.repository.produto.DepartamentoRepository;

@Component
public class DepartamentoDAO extends AbstractDAO {

	@Autowired
	public DepartamentoRepository repository;
	
	@Override
	public void salvar(IEntidade entidade) {
		EntidadeDominio dominio = (EntidadeDominio) entidade;
		if(dominio.getId() == 0) {
			repository.save(noCast(entidade));
		}
	}

	@Override
	public void alterar(IEntidade entidade) {
		EntidadeDominio dominio = (EntidadeDominio) entidade;
		if(dominio.getId() > 0) {
			repository.save(noCast(entidade));
		}
	}

	@Override
	public List<IEntidade> consultar(IEntidade entidade) {
		return noCast(repository.findAll());
	}

	@Override
	public void deletar(IEntidade entidade) {
		repository.delete(noCast(entidade));
	}

	
}
