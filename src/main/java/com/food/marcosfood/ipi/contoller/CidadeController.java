package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.exception.*;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.findAllCidade());
    }

    @GetMapping("/{cidadeId}")
    public Cidade findAllById(@PathVariable Long cidadeId) {
        try {
            return cidadeService.findByIdCidade(cidadeId);

        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade create(@RequestBody Cidade cidade) {
        try {
            return cidadeService.cadastraCidade(cidade);
        } catch (EstadoNaoEncotradoException e) {

            throw new NegocioExcepetion(e.getMessage(), e);

        }

    }

    @PutMapping("/{cidadeId}")
    public Cidade alterCidade(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {


        Cidade cidadeAtual = cidadeService.findByIdCidade(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadeService.cadastraCidade(cidadeAtual);


    }

    @DeleteMapping("/{cidadeId}")
    public void delete(@PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);


    }


}
