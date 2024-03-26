package com.food.marcosfood.api.exceptionhander;

import lombok.Getter;

@Getter
public enum ProblemaType {

    MENSAGEM_INCOMPRIENCIVEL("mensagem-incompreensivel", "Mensagem incompreensível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),

    ERRO_NEGOCIO("/negocio-exception", "Negocio Exception"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em Uso"),

    DADOS_INVALIDOS("/dados-invávalidos", "Erro de dados inválidos"),
    ERRO_DE_SISTEMA("/erro-sistema", "Erro de sistema"),
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");


    private final String title;
    private String uri;

    ProblemaType(String path, String title) {
        this.uri = "https://marcosfood.com.br/" + uri;
        this.title = title;
    }


}

