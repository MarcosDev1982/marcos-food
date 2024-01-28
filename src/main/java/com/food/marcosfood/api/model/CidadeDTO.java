package com.food.marcosfood.api.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class CidadeDTO extends CollectionModel<CidadeDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Uberlândia")
    private String nome;

    private EstadoDTO estado;

}
