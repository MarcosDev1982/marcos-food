package com.food.marcosfood.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Grupo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @ManyToMany
    @JoinTable(name = "restaurante_grupo_permissoes", joinColumns =@JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permisao_id") )
    private List<Permissao> permissoes = new ArrayList<>();


}
