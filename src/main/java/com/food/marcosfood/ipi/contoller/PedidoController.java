package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.service.PedidoService;
import com.food.marcosfood.ipi.assembler.PedidoAssembler;
import com.food.marcosfood.ipi.model.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoAssembler pedidoAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PedidoDTO> buscaTodos() {
        List<Pedido> pedidoList = pedidoService.pedidoList();
        return pedidoAssembler.toCollectonDto(pedidoList);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{pedidoId}")
    public PedidoDTO buscaPedidoPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        return pedidoAssembler.toDto(pedido);
    }




}
