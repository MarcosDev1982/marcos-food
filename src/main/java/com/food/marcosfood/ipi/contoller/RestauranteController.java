package com.food.marcosfood.ipi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.marcosfood.domain.exception.*;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.buscarTodos());
    }

    @GetMapping("/{restuaranteId}")
    public Restaurante buscarPorId(@PathVariable Long restuaranteId) {
        try {

            return restauranteService.buscarPorId(restuaranteId);

        } catch (RestuaranteNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante InsereRestaurante(@RequestBody Restaurante restaurante) {

        try {
            return restauranteService.inserir(restaurante);


        } catch (RestuaranteNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage());
        }

    }

    // estudar a aula 6.3 na verdade revisar
    @PutMapping("/teste/{restuarenteId}")
    public Restaurante updadte(@PathVariable Long restuarenteId, @RequestBody Restaurante restaurante) {

        try {
            Restaurante restauranteAtual = restauranteService.buscarPorId(restuarenteId);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return restauranteService.inserir(restauranteAtual);

        } catch (RestuaranteNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {

        restauranteService.delete(restauranteId);

    }

    @PatchMapping("/{restauranteId}")
    public Restaurante alterarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {

        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        if (restaurante == null) {
            ResponseEntity.notFound().build();
        }

        extracted(campos, restaurante);

        return updadte(restauranteId, restaurante);
    }

    private void extracted(Map<String, Object> campos, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restaurnteOrigem = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropeidade, valorPropeidade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropeidade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restaurnteOrigem);
            System.out.println(nomePropeidade + " = " + valorPropeidade + " = " + novoValor);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }


}
