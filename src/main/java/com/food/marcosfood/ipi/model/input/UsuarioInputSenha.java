package com.food.marcosfood.ipi.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInputSenha {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;
}
