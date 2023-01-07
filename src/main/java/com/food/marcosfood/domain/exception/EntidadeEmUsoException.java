package com.food.marcosfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntidadeEmUsoException extends RuntimeException {

    private static final long serialVersionUID = -1069550794627123806L;

    public EntidadeEmUsoException(String mesagem){
        super(mesagem);
    }

}
