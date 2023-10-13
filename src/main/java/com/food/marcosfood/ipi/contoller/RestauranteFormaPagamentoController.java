package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.ipi.model.input.assembler.FormaPagamentoAssembler;
import com.food.marcosfood.ipi.model.FormaPagementoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoAssembler formaPagamentoAssembler;

    @GetMapping()
    public List<FormaPagementoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        return formaPagamentoAssembler.toCollectionModel(restaurante.getFormasDePagamento());
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.desassociaFormaPagamento(restauranteId, formaPagamentoId);

    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void asossociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

    }
}
