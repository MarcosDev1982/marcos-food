package com.food.marcosfood.domain.enumeration;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Getter
public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;

    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido...statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean naoPodeAlteraPara(StatusPedido novoStatus){
        return !novoStatus.statusAnteriores.contains(this);
    }

}
