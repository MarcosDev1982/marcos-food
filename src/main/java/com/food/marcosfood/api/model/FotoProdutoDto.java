package com.food.marcosfood.api.model;

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
