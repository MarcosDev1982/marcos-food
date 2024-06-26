package com.food.marcosfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PermissaoInput {

    @NotNull
    private  Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

}
