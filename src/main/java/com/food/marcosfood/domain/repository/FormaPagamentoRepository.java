package com.food.marcosfood.domain.repository;

import com.food.marcosfood.domain.model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaDePagamento, Long> {


}
