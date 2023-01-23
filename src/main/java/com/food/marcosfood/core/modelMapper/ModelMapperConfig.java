package com.food.marcosfood.core.modelMapper;

import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.RestauranteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
       var modelMapper = new ModelMapper();
       modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
               .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
        return modelMapper;
    }
}
