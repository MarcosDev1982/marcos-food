package com.food.marcosfood.cozinha.controller;

import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.service.CozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void deveRertornaStatus200QuandoConsultarCozinha(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/cozinha/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    public void deveConter7CozinhasQuandoConsultarCozinha(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/cozinha/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                .when().get()
                .then()
                .body("", Matchers.hasSize(7))
                .body("nome", Matchers.hasItems("Indiana", "Argentina"));
    }



}

