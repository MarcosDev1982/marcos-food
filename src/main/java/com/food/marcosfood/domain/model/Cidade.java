package com.food.marcosfood.domain.model;

import com.food.marcosfood.core.validation.Gruops;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private  String nome;

    @Valid
    @ConvertGroup(from = Default.class, to = Gruops.EstadoId.class )
    @NotNull
    @ManyToOne
    private Estado estado;

}
