package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.service.PedidoService;
import com.food.marcosfood.ipi.assembler.PedidoAssembler;
import com.food.marcosfood.ipi.assembler.PedidoResumoAssembler;
import com.food.marcosfood.ipi.model.PedidoDTO;
import com.food.marcosfood.ipi.model.PedidoResumoDTO;
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
    private PedidoResumoAssembler pedidoResumoAssembler;

    @Autowired
    PedidoAssembler pedidoAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PedidoResumoDTO> buscaTodos() {
        List<Pedido> pedidoList = pedidoService.pedidoList();
        return pedidoResumoAssembler.toCollectonDto(pedidoList);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{pedidoId}")
    public PedidoDTO buscaPedidoPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarPorId(pedidoId);
        return pedidoAssembler.toDto(pedido);
    }




}
