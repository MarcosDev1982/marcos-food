package com.food.marcosfood.ipi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.marcosfood.domain.exception.CidadeNaoEncontadaException;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.RestauranteNaoEncontadaException;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.ipi.assembler.RestauranteInputDesassembler;
import com.food.marcosfood.ipi.assembler.RestauranteModelAssembler;
import com.food.marcosfood.ipi.model.RestauranteDTO;
import com.food.marcosfood.ipi.model.input.RestauranteInput;
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
    @Autowired
    private RestauranteInputDesassembler restauranteInputDesassembler;

    @GetMapping
    public List<RestauranteDTO> buscarTodos() {
        return restauranteModelAssembler.toCollectionModel(restauranteService.buscarTodos());
    }

    @GetMapping("/{restuaranteId}")
    public RestauranteDTO buscarPorId(@PathVariable Long restuaranteId) {

        Restaurante restaurante = restauranteService.buscarPorId(restuaranteId);
        return restauranteModelAssembler.toModell(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO InsereRestaurante(@RequestBody @Valid RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = restauranteInputDesassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModell(restauranteService.inserir(restaurante));


        } catch (CozinhaNaoEncontadaException | CidadeNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage());
        }

    }

    // estudar a aula 6.3 na verdade revisar
    @PutMapping("/teste/{restuarenteId}")
    public RestauranteDTO updadte(@PathVariable @Valid Long restuarenteId, @RequestBody @Valid RestauranteInput restauranteInput) {

        try {

            Restaurante restauranteAtual = restauranteService.buscarPorId(restuarenteId);
            restauranteInputDesassembler.copyToDomainObeject(restauranteInput, restauranteAtual);
            return restauranteModelAssembler.toModell(restauranteService.inserir(restauranteAtual));

        } catch (CidadeNaoEncontadaException | CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {

        restauranteService.ativar(restauranteId);

    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {

        restauranteService.inativar(restauranteId);

    }

    @PutMapping("/{restauranteId}/abrir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {

        restauranteService.abrir(restauranteId);

    }

    @DeleteMapping("/{restauranteId}/fechar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {

        restauranteService.fechar(restauranteId);

    }


    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {

        restauranteService.delete(restauranteId);

    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void AtivarMultiplos(@RequestBody List<Long> restaurantesId) {
       try {
           restauranteService.ativar(restaurantesId);
       }catch (RestauranteNaoEncontadaException e){
           throw new NegocioExcepetion(e.getMessage(), e);
       }

    }

    @DeleteMapping("/inativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void inativarMultiplos(@RequestBody List<Long> restaurantesId) {
      try {
          restauranteService.inativar(restaurantesId);
      }catch (RestauranteNaoEncontadaException e){
          throw new NegocioExcepetion(e.getMessage(), e);
      }

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


}
