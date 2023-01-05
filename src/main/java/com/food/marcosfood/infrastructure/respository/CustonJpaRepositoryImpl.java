package com.food.marcosfood.infrastructure.respository;

import com.food.marcosfood.domain.repository.CustonJpaRespostiory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustonJpaRepositoryImpl<T, ID>  extends SimpleJpaRepository<T, ID> implements CustonJpaRespostiory<T, ID> {

    private EntityManager manager;

    public CustonJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager= entityManager;
    }

    @Override
    public Optional<T> buscaPrimeiro() {
        var jpql = "from" + getDomainClass().getName();
      T entity =  manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(entity);
    }
}
