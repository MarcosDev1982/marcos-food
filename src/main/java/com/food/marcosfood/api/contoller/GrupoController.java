package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.model.Grupo;
import com.food.marcosfood.api.model.input.GrupoInput;
import com.food.marcosfood.domain.service.GrupoService;
import com.food.marcosfood.api.model.input.assembler.GrupoModelAssembler;
import com.food.marcosfood.api.model.input.assembler.GrupoModelDesassembler;
import com.food.marcosfood.api.model.GrupoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoModelDesassembler grupoModelDesassembler;

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> buscarTodos() {
        List<Grupo> grupo = grupoService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(grupoModelAssembler.toCollectionModel(grupo));

    }

    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoDTO> buscarPorId(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarPorId(grupoId);
        return ResponseEntity.status(HttpStatus.OK).body(grupoModelAssembler.toModel(grupo));
    }

    @PostMapping
    public GrupoDTO cadastrarGrupo(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoModelDesassembler.toDomainObject(grupoInput);
        GrupoDTO grupoDTO = grupoModelAssembler.toModel(grupoService.cadastraGrupo(grupo));
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO).getBody();
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO alterarGrupo(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {

        Grupo grupoAtual = grupoService.buscarPorId(grupoId);
        grupoModelDesassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = grupoService.cadastraGrupo(grupoAtual);

        return ResponseEntity.status(HttpStatus.OK).body(grupoModelAssembler.toModel(grupoAtual)).getBody();
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }


}
