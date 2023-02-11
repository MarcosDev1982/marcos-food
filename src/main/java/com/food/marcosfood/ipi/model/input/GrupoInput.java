package com.food.marcosfood.ipi.model.input;

import com.food.marcosfood.domain.model.Permissao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;

   /* @Valid
    @NotBlank
    private List<PermissaoInput> permissoes;*/



}
