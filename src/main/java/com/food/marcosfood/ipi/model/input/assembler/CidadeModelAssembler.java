package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.ipi.model.CidadeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toModell(Cidade cidade) {

        return modelMapper.map(cidade, CidadeDTO.class);

    }

    public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream().map(cidade -> toModell(cidade)).collect(Collectors.toList());
    }

}
