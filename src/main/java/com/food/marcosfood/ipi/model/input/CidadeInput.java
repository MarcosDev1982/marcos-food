package com.food.marcosfood.ipi.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {
    @NotNull
    private String nome;

    @Valid
    @NotNull
    private EstadoInput estado;
}
