package com.food.marcosfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/*@JsonFilter("pedidoFilter")*/
@Getter
@Setter
public class PedidoResumoDTO {
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataEntrega;
    private RestauranteResumDTO restaurante;
    private UsuarioDTO cliente;
}
