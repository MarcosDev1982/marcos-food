package com.food.marcosfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDto {


    private String nomeArquivo;

    private String descricao;

    private String contentType;

    private Long tamanho;

}
