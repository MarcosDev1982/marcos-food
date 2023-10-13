package com.food.marcosfood.ipi.model.input.assembler;

import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.ipi.model.PedidoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO toDto(Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public List<PedidoDTO> toCollectonDto(List<Pedido> pedidos){
        return pedidos.stream().map(pedido -> toDto(pedido)).collect(Collectors.toList());

    }


}
