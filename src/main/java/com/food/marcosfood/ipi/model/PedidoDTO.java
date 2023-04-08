package com.food.marcosfood.ipi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
@Getter
@Setter
public class PedidoDTO {
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteResumDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagementoDTO formaPagamento;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens;
}
