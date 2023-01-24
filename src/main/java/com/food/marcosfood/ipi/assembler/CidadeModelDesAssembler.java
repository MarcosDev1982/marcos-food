package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.model.Estado;
import com.food.marcosfood.domain.model.input.CidadeInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelDesAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObejct(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObeject(CidadeInput cidadeInput, Cidade cidade) {
        //para evitar org.hibernate.HibernateException: identifier of an instance of
        //com.food.marcosfood.domain.model.Cozinha was altered from 1 to 2
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);

    }


}
