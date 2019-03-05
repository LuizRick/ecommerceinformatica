package com.les.ecommerce.dao.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.repository.produto.DepartamentoRepository;

@Component
public class DepartamentoDAO extends AbstractDAO {

	@Autowired
	private DepartamentoRepository repository;
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return noCast(repository.findAll());
	}

	@Override
	public void deletar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

}
