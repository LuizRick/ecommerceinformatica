package com.les.ecommerce.rns.produto;

import java.util.Optional;

import org.springframework.context.ApplicationContext;

import com.les.ecommerce.SpringContext;
import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.produto.Produto;
import com.les.ecommerce.repository.produto.ProdutoRepository;
import com.les.ecommerce.rns.IStrategy;

public class ValidarReentradaCadastroProduto implements IStrategy {


	
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade.getId() <= 0) {
			return null;
		}
		ApplicationContext context = SpringContext.getAppContext();
		ProdutoRepository repository = (ProdutoRepository) context.getBean(ProdutoRepository.class);
		Optional<Produto> ultimo = repository.findById(entidade.getId());
		Produto atual = (Produto) entidade;
		if(ultimo.isPresent() && ultimo.get().getEstoque() < atual.getEstoque() 
				|| ultimo.get().getEstoque() > atual.getEstoque() ) {
			return "Não e permitido a reentrada no estoque, Por favor utilizar controle de estoque";
		}
		return null;
	}
}
