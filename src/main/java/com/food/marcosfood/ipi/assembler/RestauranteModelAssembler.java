package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.RestauranteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO toModell(Restaurante restaurante) {

        return modelMapper.map(restaurante, RestauranteDTO.class);


    }

    public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(restaurante -> toModell(restaurante)).collect(Collectors.toList());
    }


}
