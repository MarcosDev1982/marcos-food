package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.CidadeNaoEncontadaException;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.model.Estado;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.repository.CidadeRepository;
import com.food.marcosfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    public static final String CODIGO_NAO_PODE_SER_ENCONTRADO = "O codigo %d não pode ser encontrado";
    public static final String CIDADE_COM_CODIGO_NAO_ENCONTRADA = "A cidade com o codigo %d não foi encontrada";
    public static final String ESTADO_CODIGO_NAO_PODE_SER_REMOVIDA_EM_USO = "Estado de codigo %d não pode ser removida, pois está em uso";
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public List<Cidade> findAllCidade() {
        return cidadeRepository.findAll();
    }

    public Cidade findByIdCidade(Long cidadeId) {

        return cidadeRepository.findById(cidadeId).orElseThrow(
                () -> new CidadeNaoEncontadaException((cidadeId)));

    }

    public Cidade cadastraCidade(Cidade cidadeAtual) {
        Long estadoId = cidadeAtual.getEstado().getId();
        Estado estado = estadoService.buscarPorId(estadoId);
        cidadeAtual.setEstado(estado);
        return cidadeRepository.save(cidadeAtual);
    }

    public Cidade alteraCidade(Long cidadeId, Cidade cidade) {
        if (!cidadeRepository.existsById(cidadeId)) {
            new CidadeNaoEncontadaException( cidadeId);
        }
        cidade.setId(cidadeId);
        return cidadeRepository.save(cidade);
    }



    public void remover(Long cidadeId) {
        try {

            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontadaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(ESTADO_CODIGO_NAO_PODE_SER_REMOVIDA_EM_USO, cidadeId));
        }
    }

}

