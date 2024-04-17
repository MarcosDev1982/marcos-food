package com.food.marcosfood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PedidoInput {

    @Valid
    @NotNull
    private EnderecoInput enderecoEntraga;

    @Valid
    @NotNull
    private FormaPagamentoPedidoInput formaPagamento;

    @Valid
    @NotNull
    private RestauranteInput restaurante;

    @Valid
    @Size(min = 1)
    @NotNull
    private final List<ItemPedidoInput> itens = new ArrayList<>();



}
