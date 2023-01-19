package com.food.marcosfood.ipi.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.food.marcosfood.domain.model.Estado;

public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}
