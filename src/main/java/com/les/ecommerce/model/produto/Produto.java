package com.les.ecommerce.model.produto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
public class Produto extends EntidadeDominio {

	private String descricao;
	private String marca;
	private String modelo;
	private Double peso;
	private Double altura;
	private Double largura;
	@OneToOne
	private GrupoPrecificacao grupoPrecificacao;
	@OneToOne
	private Departamento departamento;
	private String codigoBarras;
	private Double estoque;
	private Double margeLucro;
	private Double valorCusto;
	private Double valorVenda;
	private String caracteristicas;
	private Boolean status;
	
	@Enumerated(EnumType.STRING)
	private CategoriaInativacao categoriaInativacao;
	
	private String justificativaInativacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public GrupoPrecificacao getGrupoPrecificacao() {
		return grupoPrecificacao;
	}

	public void setGrupoPrecificacao(GrupoPrecificacao grupoPrecificacao) {
		this.grupoPrecificacao = grupoPrecificacao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	public Double getMargeLucro() {
		return margeLucro;
	}

	public void setMargeLucro(Double margeLucro) {
		this.margeLucro = margeLucro;
	}

	public Double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public CategoriaInativacao getCategoriaInativacao() {
		return categoriaInativacao;
	}

	public void setCategoriaInativacao(CategoriaInativacao categoriaInativacao) {
		this.categoriaInativacao = categoriaInativacao;
	}

	public String getJustificativaInativacao() {
		return justificativaInativacao;
	}

	public void setJustificativaInativacao(String justificativaInativacao) {
		this.justificativaInativacao = justificativaInativacao;
	}

}
