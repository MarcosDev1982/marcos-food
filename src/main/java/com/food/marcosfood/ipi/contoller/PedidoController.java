package com.food.marcosfood.ipi.contoller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.service.PedidoService;
import com.food.marcosfood.ipi.assembler.PedidoAssembler;
import com.food.marcosfood.ipi.assembler.PedidoInputDesassembler;
import com.food.marcosfood.ipi.assembler.PedidoResumoAssembler;
import com.food.marcosfood.ipi.model.PedidoDTO;
import com.food.marcosfood.ipi.model.PedidoResumoDTO;
import com.food.marcosfood.ipi.model.input.PedidoInput;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoAssembler pedidoAssembler;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoResumoAssembler pedidoResumoAssembler;
    @Autowired
    private PedidoInputDesassembler pedidoInputDesassembler;

     @ResponseStatus(HttpStatus.OK)
     @GetMapping
     public List<PedidoResumoDTO> buscaTodos() {
         List<Pedido> pedidoList = pedidoService.pedidoList();
         return pedidoResumoAssembler.toCollectonDto(pedidoList);
     }
/*    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MappingJacksonValue buscaTodos(@RequestParam(required = false) String campos) {

        List<Pedido> pedidoList = pedidoService.pedidoList();
        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoResumoAssembler.toCollectonDto(pedidoList);

        MappingJacksonValue pedidosMappingJacksonValue = new MappingJacksonValue(pedidoResumoDTOS);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
        if (StringUtils.isNotBlank(campos)) {
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }
        pedidosMappingJacksonValue.setFilters(filterProvider);

        return pedidosMappingJacksonValue;

    }*/


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscaPedidoPorId(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarPorId(codigoPedido);
        return pedidoAssembler.toDto(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastraPedido(@RequestBody PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoService.cadastraPedido(pedidoInputDesassembler.toDomainObject(pedidoInput));
            return pedidoAssembler.toDto(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
