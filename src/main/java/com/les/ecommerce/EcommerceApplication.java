package com.les.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.les.ecommerce.EcommerceApplication;

@SpringBootApplication
@EntityScan({"com.les.ecommerce.model"})
@EnableJpaRepositories({"com.les.ecommerce.repository"})
@ComponentScan({"com.les.ecommerce.facade" , "com.les.ecommerce.controller" , "com.les.ecommerce.repository" , "com.les.ecommerce.command" ,
	"com.les.ecommerce.dao" , "com.les.ecommerce.helpers"})
public class EcommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
}

