package com.les.ecommerce.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.produto.Departamento;

public interface CategoriaRepository extends JpaRepository<Departamento, Long>{

}
