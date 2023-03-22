package com.food.marcosfood.domain.service;


import com.food.marcosfood.domain.exception.PedidoNaoEncontradoException;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> pedidoList(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long pedidoId){
        return pedidoRepository.findById(pedidoId).orElseThrow(()->
                new PedidoNaoEncontradoException(pedidoId));
    }



}
