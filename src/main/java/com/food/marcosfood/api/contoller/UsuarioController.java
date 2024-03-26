package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.UsuarioNaoEncontadoException;
import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.domain.service.UsuarioService;
import com.food.marcosfood.api.model.input.assembler.UsuarioDesassembler;
import com.food.marcosfood.api.model.input.assembler.UsuarioModelAssembler;
import com.food.marcosfood.api.model.UsuarioDTO;
import com.food.marcosfood.api.model.input.UsuarioAltInput;
import com.food.marcosfood.api.model.input.UsuarioInput;
import com.food.marcosfood.api.model.input.UsuarioInputSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioDesassembler usuarioDesassembler;

    @GetMapping
    public CollectionModel<UsuarioDTO> buscarTodos() {
        List<Usuario> todasUsuarios = usuarioService.buscarTodos();
        return usuarioModelAssembler.toCollectionModel(todasUsuarios);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelAssembler.toModel(usuarioService.buscarPorId(usuarioId)));
    }

    @PostMapping
    private ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = usuarioDesassembler.toDomainObject(usuarioInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelAssembler.toModel(usuarioService.salvar(usuario)));


    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioAltInput usuarioAltInput) {

        try {
            Usuario usuarioAtua = usuarioService.buscarPorId(usuarioId);

            usuarioDesassembler.copyToDomainObject(usuarioAltInput, usuarioAtua);
            usuarioAtua = usuarioService.salvar(usuarioAtua);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioModelAssembler.toModel(usuarioAtua));
        } catch (UsuarioNaoEncontadoException e) {
            throw new NegocioExcepetion(e.getMessage(), e);
        }


    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputSenha senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }


    @DeleteMapping("/usuarioId")
    public void deletaUsuario(@PathVariable Long usuarioId) {
        usuarioService.deleta(usuarioId);
    }


}
