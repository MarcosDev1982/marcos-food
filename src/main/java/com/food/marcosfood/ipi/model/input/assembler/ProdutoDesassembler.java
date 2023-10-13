package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.input.ProdutoInput;
import com.food.marcosfood.ipi.model.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDesassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }

}
