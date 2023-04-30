package com.food.marcosfood.infrastructure.respository.spec;

import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter pedidoFilter) {
        return (root, query, builder) -> {
            if (Pedido.class.equals(query.getRestriction())) {
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }


            var predicates = new ArrayList<Predicate>();

            if (pedidoFilter.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), pedidoFilter.getClienteId()));
            }
            if (pedidoFilter.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), pedidoFilter.getRestauranteId()));
            }
            if (pedidoFilter.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));
            }
            if (pedidoFilter.getDataCriacaoInicio() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getGetDataCriacaoFIm()));
            }

            return builder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }


}
