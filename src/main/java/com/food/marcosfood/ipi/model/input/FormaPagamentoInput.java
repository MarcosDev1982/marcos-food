package com.food.marcosfood.ipi.model.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoInput {
    @NotBlank
    private String descricao;
}
