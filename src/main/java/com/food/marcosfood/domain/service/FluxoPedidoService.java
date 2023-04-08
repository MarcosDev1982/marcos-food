package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void comfirmar(String pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        pedido.confirma();
    }

    @Transactional
    public void entregue(String pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        pedido.cancelar();
    }


}
