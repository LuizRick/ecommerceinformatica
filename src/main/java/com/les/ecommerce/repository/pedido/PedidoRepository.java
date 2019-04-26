package com.les.ecommerce.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.venda.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
