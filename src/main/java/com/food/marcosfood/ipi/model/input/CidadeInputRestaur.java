package com.food.marcosfood.ipi.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInputRestaur {

    @NotNull
    private Long id;

}
