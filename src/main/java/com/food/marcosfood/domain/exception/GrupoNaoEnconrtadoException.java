package com.food.marcosfood.domain.exception;

public class GrupoNaoEnconrtadoException extends EntidadeNaoEncotrada {


    public GrupoNaoEnconrtadoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEnconrtadoException(Long restauranteId ) {
        this(String.format(" O Grupo com o codigo %d não foi encontrada",restauranteId));
    }
}
