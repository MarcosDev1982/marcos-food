package com.food.marcosfood.domain.service;


import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.GrupoNaoEnconrtadoException;
import com.food.marcosfood.domain.model.Grupo;
import com.food.marcosfood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    private static final String Grupo_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO = "Grupo de codigo %d não pode ser removida, pois está em uso";

    public Grupo cadastraGrupo(Grupo grupo) {

        return grupoRepository.save(grupo);
    }

    public List<Grupo> buscarTodos() {
        return grupoRepository.findAll();
    }

    public Grupo buscarPorId(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEnconrtadoException(grupoId));
    }


    public void excluir(Long grupoId) {

        try {

            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEnconrtadoException(grupoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(Grupo_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, grupoId));
        }
    }


}
