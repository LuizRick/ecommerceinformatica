package com.les.ecommerce.model.cliente;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.les.ecommerce.model.EntidadeDominio;
import com.les.ecommerce.model.INotPersistente;

@MappedSuperclass
public class Cartao extends EntidadeDominio implements IFormaPagamento, INotPersistente{

	
	private String titular;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private BandeiraCartao bandeira;
	 
	private String codigoSeguranca;
	
	@Transient
	private boolean salvarPerfil;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate validade;
	
	@Transient
	private Double valor;

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public boolean isSalvarPerfil() {
		return salvarPerfil;
	}

	public void setSalvarPerfil(boolean salvarPerfil) {
		this.salvarPerfil = salvarPerfil;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
}
