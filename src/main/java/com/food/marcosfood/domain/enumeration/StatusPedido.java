package com.food.marcosfood.domain.enumeration;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
public enum StatusPedido {
    CRIADO,
    CONFIRMADO,
    ENTREGE,
    CANCELADO
}
