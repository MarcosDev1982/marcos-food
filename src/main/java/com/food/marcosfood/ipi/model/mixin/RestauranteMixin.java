package com.food.marcosfood.ipi.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.food.marcosfood.domain.model.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {


    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaDePagamento> formasDePagamento = new ArrayList<>();

    @JsonIgnore
    private List<Usuario> responsaveis = new ArrayList<>();


    @JsonIgnore
    private Endereco endereco;

    //@JsonIgnore
    private OffsetDateTime dataCadastro;

   // @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private List<Grupo> grupos = new ArrayList<>();



}
