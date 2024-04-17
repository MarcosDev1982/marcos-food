package com.food.marcosfood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.CollectionModel;

@Setter
@Getter
public class CidadeDTO extends CollectionModel<CidadeDTO> {


    private Long id;


    private String nome;

    private EstadoDTO estado;

}
