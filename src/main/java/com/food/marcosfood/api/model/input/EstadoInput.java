package com.food.marcosfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoInput {
    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

}
