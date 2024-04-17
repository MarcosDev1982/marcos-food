package com.food.marcosfood.infrastructure.service.query;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.service.VendaQuerYService;
import com.food.marcosfood.api.v1.model.VendaDiaria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQuerYService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendasDiariasFilter filter, String timeOffset) {



        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        var functionConverteTzCriacao = builder.function("convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDateCriacao = builder.function("date", Date.class, functionConverteTzCriacao);

        var selection = builder.construct(VendaDiaria.class,
                functionDateCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
        );

        if (filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }

        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filter.getDataCriacaoInicio()));
        }

        if (filter.getGetDataCriacaoFIm() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filter.getGetDataCriacaoFIm()));
        }
        query.where(predicates.toArray(new javax.persistence.criteria.Predicate[0]));


        query.select(selection);
        query.groupBy(functionDateCriacao);
        return entityManager.createQuery(query).getResultList();
    }
}
