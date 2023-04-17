package com.food.marcosfood.ipi.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
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
