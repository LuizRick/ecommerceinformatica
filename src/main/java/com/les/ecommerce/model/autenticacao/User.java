package com.les.ecommerce.model.autenticacao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.les.ecommerce.model.EntidadeDominio;

@Entity
@Table(name="user")
public class User extends EntidadeDominio {

	@Column(name = "email")
    private String email;
	
    @Column(name = "password")	
    private String password;
    
    @Transient
    private String confirmaPassword;
    
    @Column(name = "active")
    private int active;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;
    
    
    @Transient
    private String roleName;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmaPassword() {
		return confirmaPassword;
	}

	public void setConfirmaPassword(String confirmaPassword) {
		this.confirmaPassword = confirmaPassword;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
