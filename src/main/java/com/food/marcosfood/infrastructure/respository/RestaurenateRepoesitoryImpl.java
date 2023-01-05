package com.food.marcosfood.infrastructure.respository;

import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.repository.RestauranteRepository;
import com.food.marcosfood.domain.repository.RestaurenateRepoesitoryQuery;
import com.food.marcosfood.infrastructure.respository.spec.RestaurantesSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurenateRepoesitoryImpl implements RestaurenateRepoesitoryQuery {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

       /* var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 ");
        var parametros = new HashMap<String, Object>();


        if (StringUtils.hasLength(nome)) {
            jpql.append("where nome like : nome ");
            parametros.put("nome", "%" + nome + "%");
        }
        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= : taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }
        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= : taxaFreteFinal ");
            parametros.put("taxaFinal", taxaFreteInicial);
        }
        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));*/

        // estudar isso novamente aulas 5.14 obs inda não esta dinaminca
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)){
            predicates.add(builder.like(root.get("nome"), "%" + nome + "+"));
        }
        if(taxaFreteInicial != null){
            predicates.add(builder
                    .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (taxaFreteFinal != null){
            predicates.add(builder
                    .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
      return restauranteRepository.findAll(RestaurantesSpecs.comFreteGratis().and(RestaurantesSpecs.comNomeSemelhante(nome)));
    }

}
