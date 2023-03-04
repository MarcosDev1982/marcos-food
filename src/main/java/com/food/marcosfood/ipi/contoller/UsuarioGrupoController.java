package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.domain.service.UsuarioService;
import com.food.marcosfood.ipi.assembler.GrupoModelAssembler;
import com.food.marcosfood.ipi.model.GrupoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;


    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
     Usuario usuario = usuarioService.buscarPorId(usuarioId);
      return grupoModelAssembler.toCollectionModel(usuario.getGrupos());

    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
       usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }


}
