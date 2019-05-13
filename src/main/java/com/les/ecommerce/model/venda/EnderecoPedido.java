package com.les.ecommerce.model.venda;

import javax.persistence.Entity;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.les.ecommerce.model.cliente.Endereco;

@Entity
public class EnderecoPedido extends Endereco {

	
	public static EnderecoPedido newInstace(Endereco endereco) {
		Mapper mapper = new DozerBeanMapper();
		EnderecoPedido ep = new EnderecoPedido();
		mapper.map(endereco, ep);
		ep.setId(0);
		return ep;
	}
}
