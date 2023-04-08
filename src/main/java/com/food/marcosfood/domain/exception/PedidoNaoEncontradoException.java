package com.food.marcosfood.domain.exception;


public class PedidoNaoEncontradoException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 7211091583257941155L;



    public PedidoNaoEncontradoException(String codigoPedido ) {
        super(String.format(" O Pedido com o codigo %s não foi encontrada",codigoPedido));
    }
}
