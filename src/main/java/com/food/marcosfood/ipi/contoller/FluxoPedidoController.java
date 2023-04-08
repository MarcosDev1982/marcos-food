package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/comfirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirma(@PathVariable Long pedidoId) {

        fluxoPedidoService.comfirmar(pedidoId);

    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable Long pedidoId) {

        fluxoPedidoService.entregue(pedidoId);

    }

    @PutMapping("/cancelado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable Long pedidoId) {

        fluxoPedidoService.cancelar(pedidoId);

    }

}
