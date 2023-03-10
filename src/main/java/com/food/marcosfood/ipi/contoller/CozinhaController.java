package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.ipi.model.input.CozinhaInput;
import com.food.marcosfood.domain.service.CozinhaService;
import com.food.marcosfood.ipi.assembler.CozinhaModelAssembler;
import com.food.marcosfood.ipi.assembler.CozinhaModelDesAssembler;
import com.food.marcosfood.ipi.model.CozinhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinha")
public class CozinhaController {

    @Autowired
    CozinhaModelDesAssembler cozinhaModelDesAssembler;
    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @GetMapping("/cozinhas")
    private List<CozinhaDTO> listar() {
        return cozinhaModelAssembler.toCollectionModel(cozinhaService.list());
    }

    @GetMapping("/{cozinhaId}")
    private CozinhaDTO buscarPorId(@PathVariable Long cozinhaId) {

        Cozinha cozinha = cozinhaService.buscarPorId(cozinhaId);

        return cozinhaModelAssembler.toModell(cozinha);
    }

    @PostMapping
    private CozinhaDTO criarCozinha(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaModelDesAssembler.toDomainObeject(cozinhaInput);
        return cozinhaModelAssembler.toModell(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody CozinhaInput cozinhaInput) {
        try {
            Cozinha cozinhaAtual = cozinhaService.buscarPorId(cozinhaId);
            cozinhaModelDesAssembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
            return cozinhaModelAssembler.toModell(cozinhaService.salvar(cozinhaAtual));
        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }


    }

    @DeleteMapping("/{cozinhaId}")
    private void deletarConzinha(@PathVariable Long cozinhaId) {

        cozinhaService.deleta(cozinhaId);

    }

    @ExceptionHandler(EntidadeNaoEncotrada.class)
    public ResponseEntity<?> tratarErro(EntidadeNaoEncotrada e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


}
