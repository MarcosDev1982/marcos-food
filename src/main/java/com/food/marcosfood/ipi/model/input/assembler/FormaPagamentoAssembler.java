package com.food.marcosfood.ipi.model.input.assembler;


import com.food.marcosfood.domain.model.FormaPagamento;

import com.food.marcosfood.ipi.model.FormaPagementoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagementoDTO toModell(FormaPagamento formaDePagamento) {
        return modelMapper.map(formaDePagamento, FormaPagementoDTO.class);
    }

    public List<FormaPagementoDTO> toCollectionModel(Collection<FormaPagamento> formaDePagamentoList) {
        return formaDePagamentoList.stream().map(formaDePagamento -> toModell(formaDePagamento)).collect(Collectors.toList());

    }

}
