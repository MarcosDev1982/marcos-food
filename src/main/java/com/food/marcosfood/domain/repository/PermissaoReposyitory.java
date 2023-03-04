package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoReposyitory extends JpaRepository<Permissao, Long> {
}
