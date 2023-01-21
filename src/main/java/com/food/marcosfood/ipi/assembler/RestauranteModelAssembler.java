package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.model.input.RestauranteInput;
import com.food.marcosfood.ipi.model.CozinhaDTO;
import com.food.marcosfood.ipi.model.RestuaranteDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {
    public RestuaranteDTO toModell(Restaurante restaurante) {
        RestuaranteDTO restuaranteDTO = new RestuaranteDTO();
        CozinhaDTO cozinhaDTO = new CozinhaDTO();

        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        restuaranteDTO.setId(restaurante.getId());
        restuaranteDTO.setNome(restaurante.getNome());
        restuaranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restuaranteDTO.setCozinhaDTO(cozinhaDTO);

        return restuaranteDTO;


    }

    public List<RestuaranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(restaurante -> toModell(restaurante)).collect(Collectors.toList());
    }



}
