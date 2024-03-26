package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.service.CozinhaService;
import com.food.marcosfood.api.model.input.assembler.CozinhaModelAssembler;
import com.food.marcosfood.api.model.input.assembler.CozinhaModelDesAssembler;
import com.food.marcosfood.api.model.CozinhaDTO;
import com.food.marcosfood.api.model.input.CozinhaInput;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/cozinha")
public class CozinhaController {



    @Autowired
    CozinhaModelDesAssembler cozinhaModelDesAssembler;
    @Autowired
    private CozinhaService cozinhaService;
    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cozinhas")
    private Page<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Listando cozinhas {}", pageable.getPageSize());


        Page<Cozinha> cozinhaPage = cozinhaService.list(pageable);
        List<CozinhaDTO> cozinhaDTOList = cozinhaModelAssembler.toCollectionModel(cozinhaPage.getContent());
        Page<CozinhaDTO> cozinhaDTOPage = new PageImpl<>(cozinhaDTOList, pageable, cozinhaPage.getTotalElements());
        return cozinhaDTOPage;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscarPorId(@PathVariable Long cozinhaId) {

        Cozinha cozinha = cozinhaService.buscarPorId(cozinhaId);

        return cozinhaModelAssembler.toModell(cozinha);
    }
    @PreAuthorize("hasAnyAuthority('EDITAR_COZINHAS')")
    @PostMapping
    private CozinhaDTO criarCozinha(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaModelDesAssembler.toDomainObeject(cozinhaInput);
        return cozinhaModelAssembler.toModell(cozinhaService.salvar(cozinha));
    }
    @PreAuthorize("hasAnyAuthority('EDITAR_COZINHAS')")
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
    @PreAuthorize("hasAnyAuthority('EDITAR_COZINHAS')")
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
