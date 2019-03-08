package com.les.ecommerce.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.produto.GrupoPrecificacao;

@Repository
public interface GrupoPrecificacaoRepository extends JpaRepository<GrupoPrecificacao, Long> {
	
}
