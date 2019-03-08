package com.les.ecommerce.model.produto;

import javax.persistence.Entity;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
public class Departamento extends EntidadeDominio {


	public String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
