package com.food.marcosfood.cozinha.controller;

import static org.hamcrest.Matchers.equalTo;

import com.food.marcosfood.cozinha.DatabaseCleaner;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.repository.CozinhaRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class                                                                                    CadastroCozinhaControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @Autowired
    DatabaseCleaner databaseCleaner;


    @BeforeEach
    public void setUp() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinha/cozinhas";
        databaseCleaner.clearTables();
        preparaDados();
    }

    @Test
    public void deveRertornaStatus200QuandoConsultarCozinha() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    @Test
    public void deveConter1CozinhasQuandoConsultarCozinha() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then()
                .body("", Matchers.hasSize(1))
                .body("nome", Matchers.hasItems("Tailandesa"));
    }

    @Test
    public void deveRertornaStatus201QuandoCadastraCozinha() {

        RestAssured.given()
                .basePath("/cozinha")
                .body("{\"nome\": \"Chinesa\"}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRertornaStatus200QuandoConsultarCozinhaExistente() {

        RestAssured.given()
                .basePath("/cozinha")
                .pathParam("cozinhaId", 1)
                .accept(ContentType.JSON)
                .when().get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("Tailandesa"));
    }

    @Test
    public void deveRertornaStatus404QuandoConsultarCozinhaInexistente() {

        RestAssured.given()
                .basePath("/cozinha")
                .pathParam("cozinhaId", 100)
                .accept(ContentType.JSON)
                .when().get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

    private void preparaDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

    }


}

