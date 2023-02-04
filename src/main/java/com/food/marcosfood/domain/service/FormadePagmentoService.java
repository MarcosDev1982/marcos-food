package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.FormaPagementoNaoEncontadaException;
import com.food.marcosfood.domain.model.FormaPagamento;
import com.food.marcosfood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormadePagmentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> findAll() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento findById(Long formaPagamentoId) {

        return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new FormaPagementoNaoEncontadaException(formaPagamentoId));


    }

    @Transactional
    public FormaPagamento create(FormaPagamento formaDePagamento) {
        return formaPagamentoRepository.save(formaDePagamento);
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaDePagamento) {
        return formaPagamentoRepository.save(formaDePagamento);
    }

    @Transactional
    public void delete(Long formaPagmantoId) {
        try {
            formaPagamentoRepository.deleteById(formaPagmantoId);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagementoNaoEncontadaException(formaPagmantoId);
        }
    }
}
