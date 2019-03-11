package com.les.ecommerce.model.produto;

import javax.persistence.Entity;

import org.hibernate.envers.Audited;

import com.les.ecommerce.model.EntidadeDominio;


@Entity
@Audited
public class GrupoPrecificacao extends EntidadeDominio {

	private String nome;
	
	private Double porcentagem;

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
