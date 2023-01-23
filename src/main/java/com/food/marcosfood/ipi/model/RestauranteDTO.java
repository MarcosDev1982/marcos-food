package com.food.marcosfood.ipi.model;


import com.food.marcosfood.domain.model.Cozinha;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RestuaranteDTO {


    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinhaDTO;


}
