package com.food.marcosfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantePedidoInput {
    @NotNull
    private Long id;
}
