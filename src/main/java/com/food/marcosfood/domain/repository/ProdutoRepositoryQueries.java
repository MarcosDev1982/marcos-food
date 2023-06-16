package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.FotoProduto;
import org.springframework.stereotype.Repository;



public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);

}
