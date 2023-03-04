package com.food.marcosfood.domain.exception;

import java.io.Serial;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = -6724373353359730728L;

    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long produtoId) {
        this(String.format("A permicao com o codigo %d não foi encontrada", produtoId));
    }
}
