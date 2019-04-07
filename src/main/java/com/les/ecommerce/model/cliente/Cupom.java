package com.les.ecommerce.model.cliente;

import javax.persistence.Entity;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
public class Cupom extends EntidadeDominio implements IFormaPagamento {
	private String codigo;
	private Double valor;
	private boolean ativo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
