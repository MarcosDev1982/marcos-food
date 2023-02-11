package com.food.marcosfood.domain.exception;

public class UsuarioNaoEncontadoException extends EntidadeNaoEncotrada {


    public UsuarioNaoEncontadoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontadoException(Long restauranteId) {
        this(String.format(" O usuário com o codigo %d não foi encontrado", restauranteId));
    }

}
