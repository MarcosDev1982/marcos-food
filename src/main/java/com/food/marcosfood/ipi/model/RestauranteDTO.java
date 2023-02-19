package com.food.marcosfood.ipi.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RestauranteDTO {


    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
    private EnderecoDTO endereco;
    private List<FormaPagementoDTO> formaPagamento = new ArrayList<>();
    private boolean ativo;
    private  boolean aberto;


}
