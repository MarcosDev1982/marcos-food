package com.food.marcosfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoDTO {


    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;

}
