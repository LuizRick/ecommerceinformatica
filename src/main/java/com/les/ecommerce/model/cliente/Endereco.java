package com.les.ecommerce.model.cliente;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
@Table(name="ENDERECO")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Endereco extends EntidadeDominio {

	
	private String descricao;
	private String tipoResidencia;
	private String tipoLogradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String pais;
	private String estado;
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipo;
	
	@Transient
	private Boolean salvarPerfil;

	public Boolean getSalvarPerfil() {
		return salvarPerfil;
	}

	public void setSalvarPerfil(Boolean salvarPerfil) {
		this.salvarPerfil = salvarPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoResidencia() {
		return tipoResidencia;
	}

	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}
}
