package com.les.ecommerce.rns.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.repository.produto.ProdutoRepository;
import com.les.ecommerce.rns.IStrategy;

@Component
public class ValidarReentradaCadastroProduto implements IStrategy {

	@Autowired
	ProdutoRepository repository;

	
	@Override
	public String processar(EntidadeDominio entidade) {
		Optional<Produto> ultimo = repository.findById(entidade.getId());
		Produto atual = (Produto) entidade;
		if(ultimo.isPresent() && ultimo.get().getEstoque() < atual.getEstoque() 
				|| ultimo.get().getEstoque() > atual.getEstoque() ) {
			return "Não e permitido a reentrada no estoque, Por favor utilizar controle de estoque";
		}
		
		
		return null;
	}
}
