package com.food.marcosfood.domain.repository;


import com.food.marcosfood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    @Query("select max (dataAtualizacao) from FormaPagamento ")
    OffsetDateTime getUltimaAtualizacao();


}
