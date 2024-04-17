package com.food.marcosfood.api.v1.assembler;


import com.food.marcosfood.domain.model.FormaPagamento;
import com.food.marcosfood.api.v1.model.input.FormaPagamentoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaDePamentoDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput formaDePagamentoInput) {
        return modelMapper.map(formaDePagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaDePagamentoInput, FormaPagamento formaDePagamento) {
        modelMapper.map(formaDePagamentoInput, formaDePagamento);
    }

}
