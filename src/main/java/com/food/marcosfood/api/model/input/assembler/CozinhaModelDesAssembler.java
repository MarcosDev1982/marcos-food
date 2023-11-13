package com.food.marcosfood.api.model.input.assembler;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.api.model.input.CozinhaInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelDesAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObeject(CozinhaInput cozinhaInput) {

        return modelMapper.map(cozinhaInput, Cozinha.class);

    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }

}


