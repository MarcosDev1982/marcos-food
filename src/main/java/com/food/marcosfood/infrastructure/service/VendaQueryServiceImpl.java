package com.food.marcosfood.infrastructure.service;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.service.VendaQuerYService;
import com.food.marcosfood.ipi.model.VendaDiaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public class VendaQueryServiceImpl implements VendaQuerYService {

@PersistenceContext
private EntityManager entityManager;
    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendasDiariasFilter filter) {
       var builder = entityManager.getCriteriaBuilder();
       var query = builder.createQuery(VendaDiaria.class);
       var root = query.from(Pedido.class);

       var functionDateCriacao = builder.function("date", Date.class, root.get("dataCriacao"));

       var selection = builder.construct(VendaDiaria.class,
               functionDateCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
       );

        query.select(selection);
        query.groupBy(functionDateCriacao);
        return entityManager.createQuery(query).getResultList();
    }
}
