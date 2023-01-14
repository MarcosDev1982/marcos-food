package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.FormaPagementoNaoEncontadaException;
import com.food.marcosfood.domain.model.FormaDePagamento;
import com.food.marcosfood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormadePagmentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaDePagamento> findAll() {
        return formaPagamentoRepository.findAll();
    }

    public FormaDePagamento findById(Long formaPagamentoId) {

        return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new FormaPagementoNaoEncontadaException(formaPagamentoId));


    }

    @Transactional
    public FormaDePagamento create(FormaDePagamento formaDePagamento) {
        return formaPagamentoRepository.save(formaDePagamento);
    }


}
