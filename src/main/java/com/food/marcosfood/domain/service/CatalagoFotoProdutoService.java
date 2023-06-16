package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.model.FotoProduto;
import com.food.marcosfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalagoFotoProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> footoExist = produtoRepository.findFotoById(restauranteId, produtoId);
        if (footoExist.isPresent()) {
            produtoRepository.delete(footoExist.get());
        }
        return produtoRepository.save(foto);


    }


}
