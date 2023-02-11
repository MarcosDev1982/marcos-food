package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
