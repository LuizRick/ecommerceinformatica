package com.les.ecommerce.repository.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> , QuerydslPredicateExecutor<Produto> {
	
}
