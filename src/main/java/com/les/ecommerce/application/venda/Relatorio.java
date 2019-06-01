package com.les.ecommerce.application.venda;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.cliente.Cartao;
import com.les.ecommerce.model.cliente.Endereco;
import com.les.ecommerce.model.produto.Departamento;
import com.les.ecommerce.model.venda.StatusPedido;

public class Relatorio extends EntidadeDominio {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataInicial;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataFinal;
	
	private Departamento departamento;
	private Cartao cartao;
	private Endereco endereco;
	
	private StatusPedido status;
	
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public LocalDateTime getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}
	public LocalDateTime getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
