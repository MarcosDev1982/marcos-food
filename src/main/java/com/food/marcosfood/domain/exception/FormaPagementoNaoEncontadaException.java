package com.food.marcosfood.domain.exception;


public class FormaPagementoNaoEncontadaException extends NegocioExcepetion {

    private static final long serialVersionUID = 7211091583257941155L;

    public FormaPagementoNaoEncontadaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagementoNaoEncontadaException(Long idEstado ) {
        this(String.format(" A forma de Pagamento com o codigo %d não foi encontrada",idEstado));
    }
}
