package com.food.marcosfood.api.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.food.marcosfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RestauranteDTO {

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private EnderecoDTO endereco;
    private List<FormaPagementoDTO> formaPagamento = new ArrayList<>();
    private boolean ativo;
    private  boolean aberto;


}
