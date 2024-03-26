package com.food.marcosfood.api.contoller;

import com.food.marcosfood.core.security.CheckSecurity;
import com.food.marcosfood.domain.model.Estado;
import com.food.marcosfood.domain.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

    private EstadoService estadoRepositoryImpl;
    @CheckSecurity.Estados.PodeConsultar
    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoRepositoryImpl.list());
    }
    @CheckSecurity.Estados.PodeConsultar
    @GetMapping("/{id}")
    public Estado buscarPorId(@PathVariable Long id) {
        return estadoRepositoryImpl.buscarPorId(id);
    }

    @CheckSecurity.Estados.PodeConsultar
    @PostMapping("/inseri")
    public ResponseEntity<Estado> criarEstado(@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoRepositoryImpl.salvar(estado));
    }
    @CheckSecurity.Estados.PodeEditar
    @PutMapping("/atualizar/{estadoId}")
    public ResponseEntity<Estado> altera(@PathVariable Long estadoId, @RequestBody Estado estado) {
        estado = estadoRepositoryImpl.update(estadoId, estado);
        return ResponseEntity.status(HttpStatus.OK).body(estado);

    }
    @CheckSecurity.Estados.PodeEditar
    @DeleteMapping("/delete/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long estadoId) {

        estadoRepositoryImpl.deleta(estadoId);

    }

}
