package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.enumeration.StatusPedido;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void comfirmar(Long pedidoId){
       Pedido pedido = pedidoService.buscarPorId(pedidoId);
       if(!pedido.getStatus().equals(StatusPedido.CRIADO)){
           throw new NegocioExcepetion(String.format("Status do pedido %d não pode Ser Altrerado de %s para %s", pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO.getDescricao()));
       }
       pedido.setStatus(StatusPedido.CONFIRMADO);
       pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
