package com.food.marcosfood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.food.marcosfood.core.validation.Multiplo;
import com.food.marcosfood.core.validation.TaxaFrete;
import com.food.marcosfood.core.validation.Gruops;
import com.food.marcosfood.core.validation.ValorZeroIncluiDescricao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @PositiveOrZero
    @TaxaFrete
    @NotNull
    @Column(nullable = false)
    private BigDecimal taxaFrete;


    @Valid
    @ConvertGroup(from = Default.class, to = Gruops.CozinhaId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaDePagamento> formasDePagamento = new ArrayList<>();

    @OneToMany
    private List<Usuario> responsaveis = new ArrayList<>();

    // estudar 6.4
    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @OneToMany
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    private List<Grupo> grupos = new ArrayList<>();


}
