package com.food.marcosfood.ipi.exceptionhander;

import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExcepetionHandler {

    @ExceptionHandler(EntidadeNaoEncotrada.class)
    public ResponseEntity<?> tratarErro(EntidadeNaoEncotrada e){

        Problema problem =  Problema.builder()

                .title(e.getMessage())

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(NegocioExcepetion.class)
    public ResponseEntity<?> tratarErro(NegocioExcepetion e){

        Problema problem =  Problema.builder()
                .dateTime(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }
}
