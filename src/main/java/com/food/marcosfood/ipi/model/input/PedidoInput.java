package com.food.marcosfood.ipi.model.input;

import com.food.marcosfood.domain.enumeration.StatusPedido;
import com.food.marcosfood.domain.model.*;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
    private List<ItemPedidoInput> itens = new ArrayList<>();



}
