package com.food.marcosfood.domain.exception;


public class RestuaranteNaoEncontadaException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public RestuaranteNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public RestuaranteNaoEncontadaException(Long idEstado ) {
        this(String.format(" O restaurante com o codigo %d não foi encontrada",idEstado));
    }
}
