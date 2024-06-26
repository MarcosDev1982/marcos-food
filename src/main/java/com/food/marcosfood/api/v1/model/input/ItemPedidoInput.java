package com.food.marcosfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    private Long produtoId;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String observacao;
}
