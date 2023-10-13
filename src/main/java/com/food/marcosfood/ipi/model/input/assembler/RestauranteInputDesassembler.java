package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.input.RestauranteInput;
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

    public void copyToDomainObeject(RestauranteInput restauranteInput, Restaurante restaurante) {
        //para evitar org.hibernate.HibernateException: identifier of an instance of
        //com.food.marcosfood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        if(restaurante.getEndereco() != null){
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);

    }

}
