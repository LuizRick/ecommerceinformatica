package com.les.ecommerce.repository.autenticacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.les.ecommerce.model.autenticacao.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByRole(String role);
}
