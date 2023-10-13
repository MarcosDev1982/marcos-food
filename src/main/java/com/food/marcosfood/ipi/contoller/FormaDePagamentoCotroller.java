package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.exception.FormaPagementoNaoEncontadaException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.FormaPagamento;
import com.food.marcosfood.ipi.model.input.FormaPagamentoInput;
import com.food.marcosfood.domain.service.FormadePagmentoService;
import com.food.marcosfood.ipi.model.input.assembler.FormaDePamentoDesassembler;
import com.food.marcosfood.ipi.model.input.assembler.FormaPagamentoAssembler;
import com.food.marcosfood.ipi.model.FormaPagementoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formaPagemnto")
public class FormaDePagamentoCotroller {

    @Autowired
    private FormadePagmentoService formadePagmentoService;

    @Autowired
    private FormaPagamentoAssembler formaDePagamentoAssembler;

    @Autowired
    private FormaDePamentoDesassembler formaDePamentoDesassembler;


    @GetMapping
    public ResponseEntity<List<FormaPagementoDTO>> buscaTodas() {
        List<FormaPagementoDTO> formaDePagamento = formaDePagamentoAssembler.toCollectionModel(formadePagmentoService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(formaDePagamento);
    }

    @GetMapping("/{formaDePagamentoId}")
    public ResponseEntity<FormaPagementoDTO> buscaPorId(@PathVariable Long formaPagamentoId) {
        FormaPagementoDTO formaDePagementoDTO = formaDePagamentoAssembler.toModell(formadePagmentoService.findById(formaPagamentoId));
        return ResponseEntity.status(HttpStatus.OK).body(formaDePagementoDTO);
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
