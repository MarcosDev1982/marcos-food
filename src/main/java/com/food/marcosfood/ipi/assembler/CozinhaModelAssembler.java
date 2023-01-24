package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.CozinhaDTO;
import com.food.marcosfood.ipi.model.RestauranteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDTO toModell(Cozinha cozinha){
       return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(cozinha -> toModell(cozinha)).collect(Collectors.toList());
    }


}
