package com.food.marcosfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncotrada extends NegocioExcepetion {

    private static final long serialVersionUID = 7211091583257941155L;

    public EntidadeNaoEncotrada(String mensagem) {
        super(mensagem);
    }

}
