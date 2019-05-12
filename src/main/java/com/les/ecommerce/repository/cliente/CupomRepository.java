package com.les.ecommerce.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.cliente.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

	
}
