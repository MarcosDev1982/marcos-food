package com.food.marcosfood.domain.model;

import com.food.marcosfood.domain.enumeration.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subTotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    @ManyToOne
    private Usuario cliente;

    @Embedded
    private Endereco enderecoEntraga;

    private StatusPedido statusPedido;

    @ManyToOne
    private FormaDePagamento formaDePagamento;

    @OneToOne
    private Restaurante restaurante;


}
