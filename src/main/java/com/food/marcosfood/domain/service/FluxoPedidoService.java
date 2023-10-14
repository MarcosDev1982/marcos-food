package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void comfirmar(String pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        pedido.confirma();

        Set<String> destinatarios = new HashSet<>();

        destinatarios.add(pedido.getCliente().getEmail());

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + "- Pedido Confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(destinatarios)
                .build();

        envioEmailService.enviar(mensagem);

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
