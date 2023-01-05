package com.food.marcosfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EstadoNaoEncotradoException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public EstadoNaoEncotradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncotradoException(Long idEstado ) {
        this(String.format("O estado com o codigo %d não foi encontrada",idEstado));
    }
}
