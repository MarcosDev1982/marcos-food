package com.food.marcosfood.api.v1.contoller;

import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.api.v1.assembler.FormaPagamentoAssembler;
import com.food.marcosfood.api.v1.model.FormaPagementoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @GetMapping()
    public List<FormaPagementoDTO> listar(@PathVariable Long restauranteId, Object o) {
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        return formaPagamentoAssembler.toCollectionModel(restaurante.getFormasDePagamento());
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.desassociaFormaPagamento(restauranteId, formaPagamentoId);
        return  ResponseEntity.noContent().build();

    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> asossociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return  ResponseEntity.noContent().build();

    }
}
