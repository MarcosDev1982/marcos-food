package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.EstadoNaoEncotradoException;
import com.food.marcosfood.domain.model.Estado;
import com.food.marcosfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    private static final String CODIGO_PODE_SER_REMOVIDA_ESTA_EM_USO = "Estado de codigo %d não pode ser removida, pois está em uso";
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> list() {
        return estadoRepository.findAll();
    }

    public Estado buscarPorId(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
                () -> new EstadoNaoEncotradoException(estadoId)
        );
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void deleta(Long estadoId) {
        try {

            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncotradoException(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(CODIGO_PODE_SER_REMOVIDA_ESTA_EM_USO, estadoId));
        }

    }

    public Estado update(Long estadoId, Estado estado) {
        if (!estadoRepository.existsById(estadoId)) {
            throw new RuntimeException();
        }
        estado.setId(estadoId);
        return estadoRepository.save(estado);
    }
}
