package com.food.marcosfood.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @NotBlank*/
    @Column(nullable = false)
    private String nome;

    /*@PositiveOrZero
    @TaxaFrete
    @NotNull*/
    @Column(nullable = false)
    private BigDecimal taxaFrete;

    /* @Valid
     @ConvertGroup(from = Default.class, to = Gruops.CozinhaId.class)
     @NotNull*/
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasDePagamento = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis = new HashSet<>();

    // estudar 6.4

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    private  Boolean aberto = Boolean.TRUE;


    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;


    @OneToMany
    private List<Produto> produtos = new ArrayList<>();


    @ManyToMany
    private List<Grupo> grupos = new ArrayList<>();

    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento){
        return getFormasDePagamento().remove(formaPagamento);

    }


    public boolean adcionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasDePagamento().add(formaPagamento);
    }

    public void abrir(){
        setAberto(true);
    }

    public void fefhar(){
        setAberto(false);
    }

    public boolean removerResponsavel(Usuario usuario) {
        return getResponsaveis().remove(usuario);
    }

    public boolean adicionarResponsavel(Usuario usuario) {
        return getResponsaveis().add(usuario);
    }


    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasDePagamento().contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        return !aceitaFormaPagamento(formaPagamento);
    }

}
