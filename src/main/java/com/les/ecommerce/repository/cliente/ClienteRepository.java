package com.les.ecommerce.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
