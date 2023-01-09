package com.food.marcosfood;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.service.CozinhaService;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@SpringBootTest
public class CadastroCozinhaTests {

    @Autowired
    private CozinhaService cozinhaService;



    @Test
    public void testarCasdasrtoCozinhaComSucesso() {

        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("chinesa");

        cozinhaService.salvar(novaCozinha);


        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void testarCadastroCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    cozinhaService.salvar(novaCozinha);
                });

        assertThat(erroEsperado).isNotNull();
    }





};

