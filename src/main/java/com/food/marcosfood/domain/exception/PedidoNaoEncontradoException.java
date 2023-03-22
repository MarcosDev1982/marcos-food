package com.food.marcosfood.domain.exception;


public class PedidoNaoEncontradoException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long pedidoId ) {
        this(String.format(" O Pedido com o codigo %d não foi encontrada",pedidoId));
    }
}
