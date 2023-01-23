package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.model.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {

            return modelMapper.map(restauranteInput, Restaurante.class);

    }

}
