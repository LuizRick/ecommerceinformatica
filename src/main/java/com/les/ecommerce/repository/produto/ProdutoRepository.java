package com.les.ecommerce.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
}
