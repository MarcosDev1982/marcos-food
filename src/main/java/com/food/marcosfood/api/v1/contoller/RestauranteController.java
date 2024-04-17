package com.food.marcosfood.api.v1.contoller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.marcosfood.api.v1.model.RestauranteDTO;
import com.food.marcosfood.api.v1.model.input.RestauranteInput;
import com.food.marcosfood.api.v1.assembler.RestauranteInputDesassembler;
import com.food.marcosfood.api.v1.assembler.RestauranteModelAssembler;
import com.food.marcosfood.api.v1.model.view.RestauranteView;
import com.food.marcosfood.core.security.CheckSecurity;
import com.food.marcosfood.domain.exception.CidadeNaoEncontadaException;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.RestauranteNaoEncontadaException;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;
    @Autowired
    private RestauranteInputDesassembler restauranteInputDesassembler;


    /*   @GetMapping
       public MappingJacksonValue buscarTodos(@RequestParam(required = false) String projecao) {
           List<Restaurante> restaurantes = restauranteService.buscarTodos();
           List<RestauranteDTO> restaurantesmodel = restauranteModelAssembler.toCollectionModel(restaurantes);
           MappingJacksonValue restauranteVwrapper = new MappingJacksonValue(restaurantesmodel);

           restauranteVwrapper.setSerializationView(RestauranteView.Resumo.class);

           if ("apenas-nome".equals(projecao)) {
               restauranteVwrapper.setSerializationView(RestauranteView.ApenasNome.class);
           } else if ("completo".equals(projecao)) {
               restauranteVwrapper.setSerializationView(null);
           }

           return restauranteVwrapper;
       }*/
    @CheckSecurity.Restaurantes.PodeConsultar
    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public List<RestauranteDTO> buscarTodos() {
        return restauranteModelAssembler.toCollectionModel(restauranteService.buscarTodos());

    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteDTO> listarApenasNome() {
        return buscarTodos();
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{restuaranteId}")
    public RestauranteDTO buscarPorId(@PathVariable Long restuaranteId) {

        Restaurante restaurante = restauranteService.buscarPorId(restuaranteId);
        return restauranteModelAssembler.toModell(restaurante);
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {

        restauranteService.ativar(restauranteId);
        return  ResponseEntity.noContent().build();

    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {

        restauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();

    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{restauranteId}/abrir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {

        restauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();

    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{restauranteId}/fechar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {

        restauranteService.fechar(restauranteId);

        return ResponseEntity.noContent().build();

    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restauranteId) {

        restauranteService.delete(restauranteId);

    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void AtivarMultiplos(@RequestBody List<Long> restaurantesId) {
        try {
            restauranteService.ativar(restaurantesId);
        } catch (RestauranteNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @DeleteMapping("/inativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void inativarMultiplos(@RequestBody List<Long> restaurantesId) {
        try {
            restauranteService.inativar(restaurantesId);
        } catch (RestauranteNaoEncontadaException e) {
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
