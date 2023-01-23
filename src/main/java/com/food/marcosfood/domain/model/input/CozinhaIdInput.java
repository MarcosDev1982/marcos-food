package com.food.marcosfood.domain.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CozinhaIdInpput {

    @NotNull
    private Long id;

}
