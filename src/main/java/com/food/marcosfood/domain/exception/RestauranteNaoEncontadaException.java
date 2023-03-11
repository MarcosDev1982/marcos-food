package com.food.marcosfood.domain.exception;


public class RestauranteNaoEncontadaException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public RestauranteNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontadaException(Long restauranteId ) {
        this(String.format(" O restaurante com o codigo %d não foi encontrada",restauranteId));
    }
}
