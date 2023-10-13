package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Grupo;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.GrupoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDTO toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);

    }

    public List<GrupoDTO> toCollectionModel(Collection<Grupo> grupos){
        return grupos.stream().map(grupo -> toModel(grupo)).collect(Collectors.toList());
    }




}
