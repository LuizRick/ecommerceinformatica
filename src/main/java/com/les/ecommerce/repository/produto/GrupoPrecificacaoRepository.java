package com.les.ecommerce.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.produto.GrupoPrecificacao;

public interface GrupoPrecificacaoRepository extends JpaRepository<GrupoPrecificacao, Long> {
	
}
