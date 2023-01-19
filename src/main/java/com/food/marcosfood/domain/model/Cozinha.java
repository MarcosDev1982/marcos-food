package com.food.marcosfood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.food.marcosfood.core.validation.Gruops;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @NotNull(groups = Gruops.CozinhaId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;


    @OneToMany(mappedBy = "cozinha" )
    private List<Restaurante> restaurantes = new ArrayList<>();


}
