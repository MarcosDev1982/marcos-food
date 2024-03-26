package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends CustonJpaRespostiory<Restaurante, Long>, JpaSpecificationExecutor<Restaurante>{

    @Query("from Restaurante r join r.cozinha")
    List<Restaurante>findAll();

    boolean existsResponsavel(Long restauranteId, Long usuarioId);
}
