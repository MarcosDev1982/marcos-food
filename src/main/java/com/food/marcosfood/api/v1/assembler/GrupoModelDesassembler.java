package com.food.marcosfood.api.v1.assembler;

import com.food.marcosfood.domain.model.Grupo;
import com.food.marcosfood.api.v1.model.input.GrupoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInput grupoInput) {

        return modelMapper.map(grupoInput, Grupo.class);

    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }

}
