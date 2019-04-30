package com.les.ecommerce.repository.pedido;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.venda.Pedido;

@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
