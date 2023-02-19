package com.food.marcosfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 5056511497878114311L;

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("O Produto com o codigo %d não foi encontrada", produtoId));
    }
}
