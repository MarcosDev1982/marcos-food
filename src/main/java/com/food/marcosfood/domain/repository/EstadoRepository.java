package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
