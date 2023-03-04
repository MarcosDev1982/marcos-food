package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.PermissaoNaoEncontradaException;
import com.food.marcosfood.domain.model.Permissao;
import com.food.marcosfood.domain.repository.PermissaoReposyitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisaoService {

    @Autowired
    PermissaoReposyitory permissaoReposyitory;

    public Permissao buscaPorId(Long permissaoId){
        return permissaoReposyitory.findById(permissaoId).orElseThrow(()-> new PermissaoNaoEncontradaException(permissaoId));
    }

}
