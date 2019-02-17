package com.les.ecommerce.model.autenticacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
@Table(name = "role")
public class Role extends EntidadeDominio {
	
	@Column(name="role")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
