package com.food.marcosfood.api.contoller;

import com.food.marcosfood.api.model.CidadeDTO;
import com.food.marcosfood.api.model.input.CidadeInput;
import com.food.marcosfood.api.model.input.assembler.CidadeModelAssembler;
import com.food.marcosfood.api.model.input.assembler.CidadeModelDesAssembler;
import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EstadoNaoEncotradoException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeModelDesAssembler cidadeModelDesAssembler;

    @ApiOperation("Lista Cidades")
    @GetMapping
    public List<CidadeDTO> findAll() {

        return cidadeModelAssembler.toCollectionModel(cidadeService.findAllCidade());
    }

    @ApiOperation("Busca Cidade por Id")
    @GetMapping("/{cidadeId}")
    public CidadeDTO findAllById(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
        try {

            return cidadeModelAssembler.toModell(cidadeService.findByIdCidade(cidadeId));

        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }
    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO create(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeModelDesAssembler.toDomainObejct(cidadeInput);
            return cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidade));
        } catch (EstadoNaoEncotradoException e) {

            throw new NegocioExcepetion(e.getMessage(), e);

        }

    }
    @ApiOperation("Altera uma cidade")
    @PutMapping("/{cidadeId}")
    public CidadeDTO alterCidade(@ApiParam(value = "ID de um caidade", example = "1") @PathVariable Long cidadeId, @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados") @RequestBody @Valid CidadeInput cidadeInput) {


        Cidade cidadeAtual = cidadeService.findByIdCidade(cidadeId);
        cidadeModelDesAssembler.copyToDomainObeject(cidadeInput, cidadeAtual);
        return cidadeModelAssembler.toModell(cidadeService.cadastraCidade(cidadeAtual));


    }
    @ApiOperation("Exclui uma cidade")
    @DeleteMapping("/{cidadeId}")
    public void delete(@ApiParam(value = "ID de um caidade", example = "1")@PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);


    }


}
