package com.calebematos.askfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.calebematos.askfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
public class AskfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AskfoodApiApplication.class, args);
	}

}
