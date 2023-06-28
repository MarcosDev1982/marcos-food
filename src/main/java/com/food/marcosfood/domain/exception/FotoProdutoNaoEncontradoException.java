package com.food.marcosfood.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncotrada {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }
}
