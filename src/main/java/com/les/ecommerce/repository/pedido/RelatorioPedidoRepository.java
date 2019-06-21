package com.les.ecommerce.repository.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.application.venda.Relatorio;
import com.les.ecommerce.model.venda.Pedido;

@Repository
public interface RelatorioPedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p where p.created BETWEEN :#{#relatorio.dataInicial} AND :#{#relatorio.dataFinal} ORDER BY p.created")
	public List<Pedido> findPedidoByDateRelatorio(@Param("relatorio") Relatorio relatorio);
}
