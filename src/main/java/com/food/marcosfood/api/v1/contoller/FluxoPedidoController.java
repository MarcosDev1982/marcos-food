package com.food.marcosfood.api.v1.contoller;

import com.food.marcosfood.core.security.CheckSecurity;
import com.food.marcosfood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;
    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PutMapping("/comfirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirma(@PathVariable String codigoPedido) {

        fluxoPedidoService.comfirmar(codigoPedido);
        return  ResponseEntity.noContent().build();

    }
    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PutMapping("/entregue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregue(@PathVariable String codigoPedido) {

        fluxoPedidoService.entregue(codigoPedido);
        return  ResponseEntity.noContent().build();

    }
    @CheckSecurity.Pedidos.PodeGerenciarPedidos
    @PutMapping("/cancelado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancela(@PathVariable String codigoPedido) {

        fluxoPedidoService.cancelar(codigoPedido);
        return  ResponseEntity.noContent().build();

    }

}
