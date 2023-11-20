package com.food.marcosfood.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Cidade {

    @ApiModelProperty(value = "Id da cidade", example = "1")
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Id da cidade", example = "1")
    @Column(nullable = false)
    private String nome;


    @ManyToOne
    private Estado estado;

}
