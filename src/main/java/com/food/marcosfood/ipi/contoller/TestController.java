package com.food.marcosfood.ipi.contoller;


import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private RestauranteRepository restauranteRepository;

   /* @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(
            String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }
*/

  /*
         precisa estudar mais

    @GetMapping("/restaurantes/retaurante-com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(
            String nome){

        return restauranteRepository. findComFreteGratis(nome);
    }
*/

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantesComFreteGratis(){

        return restauranteRepository.buscaPrimeiro();
    }

}
