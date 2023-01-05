package com.food.marcosfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioExcepetion extends RuntimeException {

    private static final long serialVersionUID = 7211091583257941155L;

    public NegocioExcepetion(String mensagem) {
        super(mensagem);
    }

    public NegocioExcepetion (String mensagem, Throwable causa){
           super(mensagem, causa);
    }

}
