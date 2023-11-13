package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.exception.FormaPagementoNaoEncontadaException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.FormaPagamento;
import com.food.marcosfood.api.model.input.FormaPagamentoInput;
import com.food.marcosfood.domain.repository.FormaPagamentoRepository;
import com.food.marcosfood.domain.service.FormadePagmentoService;
import com.food.marcosfood.api.model.input.assembler.FormaDePamentoDesassembler;
import com.food.marcosfood.api.model.input.assembler.FormaPagamentoAssembler;
import com.food.marcosfood.api.model.FormaPagementoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formas-pagemento")
public class FormaDePagamentoCotroller {

    @Autowired
    private FormadePagmentoService formadePagmentoService;

    @Autowired
    private FormaPagamentoAssembler formaDePagamentoAssembler;

    @Autowired
    private FormaDePamentoDesassembler formaDePamentoDesassembler;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public ResponseEntity<List<FormaPagementoDTO>> buscaTodas(ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataUtimaAtualizacao = formaPagamentoRepository.getUltimaAtualizacao();
        if(!dataUtimaAtualizacao.equals(null)){
            eTag = String.valueOf(dataUtimaAtualizacao.toEpochSecond());
        }
        if (request.checkNotModified(eTag)){
            return null;
        }

        List<FormaPagementoDTO> formaDePagamento = formaDePagamentoAssembler.toCollectionModel(formadePagmentoService.findAll());
        return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaDePagamento);
    }

    @GetMapping("/{formaDePagamentoId}")
    public ResponseEntity<FormaPagementoDTO> buscaPorId(@PathVariable Long formaPagamentoId) {
        FormaPagementoDTO formaDePagementoDTO = formaDePagamentoAssembler.toModell(formadePagmentoService.findById(formaPagamentoId));


        return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.maxAge(10,TimeUnit.SECONDS))
                .body(formaDePagementoDTO);
    }

    @PostMapping
    public ResponseEntity<FormaPagementoDTO> cadastroFormaDePagamento(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        try {
            FormaPagamento formaDePagamento = formaDePamentoDesassembler.toDomainObject(formaPagamentoInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(formaDePagamentoAssembler.toModell(formadePagmentoService.salvar(formaDePagamento)));
        } catch (FormaPagementoNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage());
        }

    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagementoDTO> alterarFormaPagamento(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaDePagamentoInput) {
        try {
            FormaPagamento formaDePagamentoAtual = formadePagmentoService.findById(formaPagamentoId);
            formaDePamentoDesassembler.copyToDomainObject(formaDePagamentoInput, formaDePagamentoAtual);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(formaDePagamentoAssembler.toModell(formadePagmentoService.salvar(formaDePagamentoAtual)));
        } catch (FormaPagementoNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }

    }

    @DeleteMapping("{formaPagmantoId}")
    public void deltarFormarPagamento(@PathVariable Long formaPagmantoId) {
        formadePagmentoService.delete(formaPagmantoId);
    }


}
