package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.Grupo;
import com.food.marcosfood.domain.service.GrupoService;
import com.food.marcosfood.ipi.model.input.assembler.PermissaoModelAssembler;
import com.food.marcosfood.ipi.model.PermissaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarPorId(grupoId);
        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermicao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);
    }


}
