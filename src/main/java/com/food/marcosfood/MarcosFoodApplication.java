package com.food.marcosfood;

import com.food.marcosfood.domain.repository.CustonJpaRespostiory;
import com.food.marcosfood.infrastructure.respository.CustonJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustonJpaRepositoryImpl.class)
public class MarcosFoodApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(MarcosFoodApplication.class, args);

	}

}
