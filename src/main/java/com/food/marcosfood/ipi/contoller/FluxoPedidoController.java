package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/comfirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirma(@PathVariable String codigoPedido) {

        fluxoPedidoService.comfirmar(codigoPedido);

    }

    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregue(@PathVariable String codigoPedido) {

        fluxoPedidoService.entregue(codigoPedido);

    }

    @PutMapping("/cancelado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable String codigoPedido) {

        fluxoPedidoService.cancelar(codigoPedido);

    }

}
