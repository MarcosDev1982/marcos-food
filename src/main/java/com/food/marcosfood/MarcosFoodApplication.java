package com.food.marcosfood;

import com.food.marcosfood.domain.repository.CustonJpaRespostiory;
import com.food.marcosfood.infrastructure.respository.CustonJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustonJpaRepositoryImpl.class)
public class MarcosFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcosFoodApplication.class, args);

	}

}
