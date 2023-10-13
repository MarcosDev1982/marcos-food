package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Permissao;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.PermissaoDTO;
import com.food.marcosfood.ipi.model.RestauranteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toModell(Permissao permissao) {

        return modelMapper.map(permissao, PermissaoDTO.class);


    }

    public List<PermissaoDTO> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream().map(permissao -> toModell(permissao)).collect(Collectors.toList());
    }


}
