package com.food.marcosfood.core.modelMapper;

import com.food.marcosfood.domain.model.Endereco;
import com.food.marcosfood.domain.model.ItemPedido;
import com.food.marcosfood.api.model.EnderecoDTO;
import com.food.marcosfood.api.model.input.ItemPedidoInput;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
       /*modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
               .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);*/


        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
        enderecoToModelTypeMap.<String>addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value)
        );
        return modelMapper;

    }
}
