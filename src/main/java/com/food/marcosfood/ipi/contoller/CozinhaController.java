package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.exception.CozinhaNaoEncontadaException;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.RestuaranteNaoEncontadaException;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cozinha")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/cozinhas")
    private ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.list());
    }

    @GetMapping("/{cozinhaId}")
    private Cozinha buscarPorId(@PathVariable Long cozinhaId) {
        return cozinhaService.buscarPorId(cozinhaId);
    }

    @PostMapping
    private ResponseEntity<Cozinha> criarCozinha(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaAtual = cozinhaService.buscarPorId(cozinhaId);
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            return cozinhaService.salvar(cozinhaAtual);
        } catch (CozinhaNaoEncontadaException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }


    }

    @DeleteMapping("/{cozinhaId}")
    private void deletarConzinha(@PathVariable Long cozinhaId) {

        cozinhaService.deleta(cozinhaId);

    }

    @ExceptionHandler(EntidadeNaoEncotrada.class)
    public ResponseEntity<?> tratarErro(EntidadeNaoEncotrada e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


}
