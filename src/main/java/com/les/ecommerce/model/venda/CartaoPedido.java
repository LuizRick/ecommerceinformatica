package com.les.ecommerce.model.venda;

import java.util.Collections;

import javax.persistence.Entity;

import org.dozer.DozerBeanMapper;

import com.les.ecommerce.model.cliente.Cartao;

@Entity
public class CartaoPedido extends Cartao {

	private Double valorUtilizado;
	
	public static CartaoPedido newInstance(Cartao cartao) {
		DozerBeanMapper  mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
		CartaoPedido cc = new CartaoPedido();
		mapper.map(cartao, cc);
		cc.setId(0);
		cc.setValorUtilizado(cartao.getValor());
		return cc;
	}

	public Double getValorUtilizado() {
		return valorUtilizado;
	}

	public void setValorUtilizado(Double valorUtilizado) {
		this.valorUtilizado = valorUtilizado;
	}
}
