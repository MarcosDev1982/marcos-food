package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.ipi.model.PedidoDTO;
import com.food.marcosfood.ipi.model.PedidoResumoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDTO toDto(Pedido pedido){
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public List<PedidoResumoDTO> toCollectonDto(List<Pedido> pedidos){
        return pedidos.stream().map(pedido -> toDto(pedido)).collect(Collectors.toList());

    }


}
