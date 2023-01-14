package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.repository.CozinhaRepository;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CozinhaService {

    private static final String COZINHA_NAO_ENCONTRADA = "Não existe um cadasto de cozinha %d";
    private static final String COZINHA_DE_CÓDIGO_NAO_PODE_SER_REMOVIDO = "Cozinha de código %d não pode ser remivdo pois está em uso";

    @PersistenceContext
    private EntityManager managed;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> list() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarPorId(Long cozinhaId) {

        return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontadaException(cozinhaId));

    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);

    }

    @Transactional
    public void deleta(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontadaException(cozinhaId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(COZINHA_DE_CÓDIGO_NAO_PODE_SER_REMOVIDO, cozinhaId));
        }

    }

 /*   public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
              ()-> new EntidadeNaoEncotrada(String.format("Não existe um cadasto de cozinha %d", cozinhaId))
        );
    }
*/

  /*  public void deleta2(Cozinha cozinhaId){
        try {
            cozinhaRepository.delete(cozinhaId);
        }catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Não existe um cadasto de cozinha %d", cozinhaId));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("Cozinha de código %d não pode ser remivdo pois está em uso", cozinhaId));
        }

    }*/


  /*  public List<Cozinha> findByname(String nome){
        return managed.createQuery(" select from cwhere nome = :nome", Cozinha.class)
                .setParameter("nome" , nome)
                .getResultList();
    }
*/

}
