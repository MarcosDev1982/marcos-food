package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.ProdutoNaoEncontradoException;
import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscaTodos(){
        return produtoRepository.findAll();
    }

    public Produto buscaPorId(Long restauranteId, Long prudotoId ){
        return produtoRepository.findById(prudotoId).orElseThrow(()-> new ProdutoNaoEncontradoException(restauranteId,prudotoId));
    }

    @Transactional
    public Produto inserirProduto(Produto produto){
        return produtoRepository.save(produto);
    }

}
