package com.les.ecommerce.repository.autenticacao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.les.ecommerce.model.autenticacao.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email,String password);
}
