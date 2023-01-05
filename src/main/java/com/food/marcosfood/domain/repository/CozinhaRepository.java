package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
//    List<Cozinha> consultarporNome(String nome);
}
