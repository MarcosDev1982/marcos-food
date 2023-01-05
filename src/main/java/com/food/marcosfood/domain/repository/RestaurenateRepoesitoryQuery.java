package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


public interface RestaurenateRepoesitoryQuery {
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
    List<Restaurante> findComFreteGratis(String nome);
}
