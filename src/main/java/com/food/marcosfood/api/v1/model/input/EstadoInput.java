package com.food.marcosfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoInput {

    @NotNull
    private Long id;

}
