package com.les.ecommerce.repository.autenticacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.autenticacao.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByRole(String role);
}
