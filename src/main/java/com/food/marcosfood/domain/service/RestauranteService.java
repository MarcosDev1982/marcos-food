package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.RestuaranteNaoEncontadaException;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    private static final String RESTAURANTE_NAO_ENCONTRADO = "O codigo %d não pode ser encontrado";
    private static final String NAO_EXISTE_COZINHA_COM_CODIGO = "Não existe Cozinha com codigo %d";
    private static final String ESTADO_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO = "Estado de codigo %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    public List<Restaurante> buscarTodos() {

        return restauranteRepository.findAll();

    }


    public Restaurante buscarPorId(Long retuaranteId) {


        return restauranteRepository.findById(retuaranteId).orElseThrow(
                () -> new RestuaranteNaoEncontadaException(retuaranteId));


    }

    @Transactional
    public Restaurante inserir(Restaurante restaurante) {

        Long cozinhdaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarPorId(cozinhdaId);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);

    }

    public Restaurante updadte(Long restauranteId, Restaurante restaurante) {
        if (!restauranteRepository.existsById(restauranteId)) {
            throw new RestuaranteNaoEncontadaException(restauranteId);
        }
        restaurante.setId(restauranteId);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void delete(Long restuaranteId) {
        try {

            restauranteRepository.deleteById(restuaranteId);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestuaranteNaoEncontadaException(restuaranteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(ESTADO_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, restuaranteId));
        }
    }


}
