package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EstadoNaoEncotradoException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.api.model.input.CidadeInput;
import com.food.marcosfood.domain.service.CidadeService;
import com.food.marcosfood.api.model.input.assembler.CidadeModelAssembler;
import com.food.marcosfood.api.model.input.assembler.CidadeModelDesAssembler;
import com.food.marcosfood.api.model.CidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeModelDesAssembler cidadeModelDesAssembler;

    @GetMapping
    public List<CidadeDTO> findAll() {

        return cidadeModelAssembler.toCollectionModel(cidadeService.findAllCidade());
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO findAllById(@PathVariable Long cidadeId) {
        try {

            return cidadeModelAssembler.toModell(cidadeService.findByIdCidade(cidadeId));

        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO create(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeModelDesAssembler.toDomainObejct(cidadeInput);
            return cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidade));
        } catch (EstadoNaoEncotradoException e) {

            throw new NegocioExcepetion(e.getMessage(), e);

        }

    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO alterCidade(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {


        Cidade cidadeAtual = cidadeService.findByIdCidade(cidadeId);
        cidadeModelDesAssembler.copyToDomainObeject(cidadeInput, cidadeAtual);
        return cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidadeAtual));


    }

    @DeleteMapping("/{cidadeId}")
    public void delete(@PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);


    }


}
