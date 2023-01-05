package com.food.marcosfood.domain.exception;


public class CozinhaNaoEncontadaException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public CozinhaNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontadaException(Long idEstado ) {
        this(String.format(" A cozinha com o codigo %d não foi encontrada",idEstado));
    }
}
