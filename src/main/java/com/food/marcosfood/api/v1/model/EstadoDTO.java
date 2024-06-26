package com.food.marcosfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class EstadoDTO extends RepresentationModel<EstadoDTO> {

    private Long id;
    private String nome;
}
