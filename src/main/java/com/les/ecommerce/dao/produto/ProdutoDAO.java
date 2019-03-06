package com.les.ecommerce.dao.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import com.les.ecommerce.dao.AbstractDAO;
import com.les.ecommerce.helpers.StringHelper;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.repository.produto.ProdutoRepository;

@Component
public class ProdutoDAO extends AbstractDAO {

	@Autowired
	private ProdutoRepository repository;
	
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
		Produto produto = (Produto) entidade;
		List<Predicate<Produto>> allPredicates = new ArrayList<Predicate<Produto>>();
		if(produto.getId() > 0)
			allPredicates.add(p -> p.getId() == produto.getId());
		if(!StringHelper.isNullOrEmpty(produto.getDescricao()))
			allPredicates.add(p -> p.getDescricao().contains(produto.getDescricao()));
		if(!StringHelper.isNullOrEmpty(produto.getMarca()))
			allPredicates.add(p -> p.getMarca().contains(produto.getMarca()));
		if(produto.getPeso() > 0)
			allPredicates.add(p-> p.getPeso() == produto.getPeso());
		if(produto.getAltura() > 0)
			allPredicates.add(p-> p.getAltura() == produto.getAltura());
		if(!StringHelper.isNullOrEmpty(produto.getCodigoBarras()))
			allPredicates.add(p -> p.getCodigoBarras().equalsIgnoreCase(produto.getCodigoBarras()));
		if(produto.getDepartamento().getId() > 0)
			allPredicates.add(p -> p.getDepartamento().getId() == produto.getDepartamento().getId());
		return null;
	}

	@Override
	public void deletar(EntidadeDominio entidade) {
		repository.delete(noCast(entidade));
	}

}
