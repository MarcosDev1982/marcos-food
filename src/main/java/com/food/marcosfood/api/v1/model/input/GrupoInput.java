package com.food.marcosfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;

   /* @Valid
    @NotBlank
    private List<PermissaoInput> permissoes;*/



}
