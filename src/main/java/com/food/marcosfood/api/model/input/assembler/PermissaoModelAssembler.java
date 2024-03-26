package com.food.marcosfood.api.model.input.assembler;

import com.food.marcosfood.core.links.MarcosLinkins;
import com.food.marcosfood.domain.model.Permissao;
import com.food.marcosfood.api.model.PermissaoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toModel(Permissao permissao) {

        return modelMapper.map(permissao, PermissaoDTO.class);
    }





    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(MarcosLinkins.linkToPermissoes());
    }


}
