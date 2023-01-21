package com.food.marcosfood.ipi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.RestuaranteNaoEncontadaException;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.model.input.RestauranteInput;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.ipi.assembler.RestauranteModelAssembler;
import com.food.marcosfood.ipi.model.RestuaranteDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @GetMapping
    public List<RestuaranteDTO> buscarTodos() {
        return restauranteModelAssembler.toCollectionModel(restauranteService.buscarTodos());
    }

    @GetMapping("/{restuaranteId}")
    public RestuaranteDTO buscarPorId(@PathVariable Long restuaranteId) {

        Restaurante restaurante = restauranteService.buscarPorId(restuaranteId);
        return restauranteModelAssembler.toModell(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestuaranteDTO InsereRestaurante(@RequestBody @Valid RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = toDomainObject(restauranteInput);
            Restaurante restaurante1 = restauranteService.inserir(restaurante);
            return restauranteModelAssembler.toModell(restaurante1);


        } catch (RestuaranteNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage());
        }

    }

    // estudar a aula 6.3 na verdade revisar
    @PutMapping("/teste/{restuarenteId}")
    public RestuaranteDTO updadte(@PathVariable @Valid Long restuarenteId, @RequestBody RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = toDomainObject(restauranteInput);

            Restaurante restauranteAtual = restauranteService.buscarPorId(restuarenteId);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
            return restauranteModelAssembler.toModell(restauranteService.inserir(restauranteAtual));

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

   /* @PatchMapping("/{restauranteId}")
    public RestuaranteDTO alterarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restaurante = toDomainObject(restauranteInput);
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        if (restaurante == null) {
            ResponseEntity.notFound().build();
        }

        extracted(campos, restaurante);

        return updadte(restauranteId, restaurante);
    }*/

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

    private Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        Cozinha cozinha = new Cozinha();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        cozinha.setId(restauranteInput.getCozinhaIput().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;


    }


}
