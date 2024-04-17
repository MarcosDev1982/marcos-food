package com.food.marcosfood.api.v1.contoller;

import com.food.marcosfood.api.ResourceUriHelper;
import com.food.marcosfood.api.v1.assembler.CidadeModelAssembler;
import com.food.marcosfood.api.v1.assembler.CidadeModelDesAssembler;
import com.food.marcosfood.api.v1.model.CidadeDTO;
import com.food.marcosfood.api.v1.model.input.CidadeInput;
import com.food.marcosfood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.food.marcosfood.core.security.CheckSecurity;
import com.food.marcosfood.core.web.MarcosMediaTypes;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EstadoNaoEncotradoException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/cidades", produces = MarcosMediaTypes.V1_APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeModelDesAssembler cidadeModelDesAssembler;

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping
    public CollectionModel<CidadeDTO> findAll() {

        List<Cidade> todasCidades = cidadeService.findAllCidade();

        return cidadeModelAssembler.toCollectionModel(todasCidades);

    }

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping("/{cidadeId}")
    public CidadeDTO findAllById(@PathVariable Long cidadeId) {
        try {

            return cidadeModelAssembler.toModel(cidadeService.findByIdCidade(cidadeId));

        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }


    }

    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO create(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeModelDesAssembler.toDomainObejct(cidadeInput);
            CidadeDTO cidadeDTO = cidadeModelAssembler.toModel(cidadeService.cadastraCidade(cidade));

            ResourceUriHelper.addUriResponseHeder(cidadeDTO.getId());

            return cidadeDTO;
        } catch (EstadoNaoEncotradoException e) {

            throw new NegocioExcepetion(e.getMessage(), e);

        }

    }

    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{cidadeId}")
    public CidadeDTO alterCidade(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {


        Cidade cidadeAtual = cidadeService.findByIdCidade(cidadeId);
        cidadeModelDesAssembler.copyToDomainObeject(cidadeInput, cidadeAtual);
        return cidadeModelAssembler.toModel(cidadeService.cadastraCidade(cidadeAtual));


    }

    @CheckSecurity.Cidades.PodeEditar
    @DeleteMapping("/{cidadeId}")
    public void delete(@PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);


    }


}
