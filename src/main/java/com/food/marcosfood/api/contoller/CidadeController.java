package com.food.marcosfood.api.contoller;

import com.food.marcosfood.api.ResourceUriHelper;
import com.food.marcosfood.api.model.CidadeDTO;
import com.food.marcosfood.api.model.input.CidadeInput;
import com.food.marcosfood.api.model.input.assembler.CidadeModelAssembler;
import com.food.marcosfood.api.model.input.assembler.CidadeModelDesAssembler;
import com.food.marcosfood.core.openapi.controller.CidadeControllerOpenApi;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EstadoNaoEncotradoException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.service.CidadeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

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


            CidadeDTO cidadeDTO = cidadeModelAssembler.toModell(cidadeService.findByIdCidade(cidadeId));

            cidadeDTO.add(linkTo(methodOn(CidadeController.class)
                    .findAllById(cidadeDTO.getId()))
                    .withSelfRel());

            cidadeDTO.add((linkTo(methodOn(CidadeController.class)
                    .findAll()).withRel("cidades")));

            cidadeDTO.getEstado().add(linkTo(methodOn(EstadoController.class)
                    .buscarPorId(cidadeDTO.getEstado().getId()))
                    .withSelfRel());

            return cidadeDTO;
        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO create(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeModelDesAssembler.toDomainObejct(cidadeInput);
            CidadeDTO cidadeDTO = cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidade));

            ResourceUriHelper.addUriResponseHeder(cidadeDTO.getId());

            return cidadeDTO;
        } catch (EstadoNaoEncotradoException e) {

            throw new NegocioExcepetion(e.getMessage(), e);

        }

    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO alterCidade(@ApiParam(value = "ID de um caidade", example = "1") @PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput) {


        Cidade cidadeAtual = cidadeService.findByIdCidade(cidadeId);
        cidadeModelDesAssembler.copyToDomainObeject(cidadeInput, cidadeAtual);
        return cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidadeAtual));


    }

    @DeleteMapping("/{cidadeId}")
    public void delete(@ApiParam(value = "ID de um caidade", example = "1") @PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);


    }


}
