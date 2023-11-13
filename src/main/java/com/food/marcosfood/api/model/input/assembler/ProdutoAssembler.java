package com.food.marcosfood.api.model.input.assembler;

import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.api.model.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
        return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
    }
}
