package com.food.marcosfood.ipi.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.food.marcosfood.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {


    @JsonIgnore
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private List<Restaurante> restaurantes = new ArrayList<>();

}
