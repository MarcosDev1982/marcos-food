package com.food.marcosfood.domain.exception;


public class CidadeNaoEncontadaException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public CidadeNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontadaException(Long idEstado ) {
        this(String.format("A cidade com o codigo %d não foi encontrada",idEstado));
    }
}
