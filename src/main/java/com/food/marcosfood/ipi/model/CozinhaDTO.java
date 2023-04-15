package com.food.marcosfood.ipi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.food.marcosfood.ipi.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class CozinhaDTO {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
