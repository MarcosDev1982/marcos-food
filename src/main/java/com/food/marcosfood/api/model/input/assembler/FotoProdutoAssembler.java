package com.food.marcosfood.api.model.input.assembler;

import com.food.marcosfood.domain.model.FotoProduto;
import com.food.marcosfood.api.model.FotoProdutoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDto toModell(FotoProduto fotoProduto) {

        return modelMapper.map(fotoProduto, FotoProdutoDto.class);

    }


}
