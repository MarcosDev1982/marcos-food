package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.UsuarioNaoEncontadoException;
import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.domain.service.UsuarioService;
import com.food.marcosfood.ipi.model.input.assembler.UsuarioDesassembler;
import com.food.marcosfood.ipi.model.input.assembler.UsuarioModelAssembler;
import com.food.marcosfood.ipi.model.UsuarioDTO;
import com.food.marcosfood.ipi.model.input.UsuarioAltInput;
import com.food.marcosfood.ipi.model.input.UsuarioInput;
import com.food.marcosfood.ipi.model.input.UsuarioInputSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelAssembler.toCollectionModel(usuarioService.buscarTodos()));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelAssembler.toModell(usuarioService.buscarPorId(usuarioId)));
    }

    @PostMapping
    private ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = usuarioDesassembler.toDomainObject(usuarioInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelAssembler.toModell(usuarioService.salvar(usuario)));


    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioAltInput usuarioAltInput) {

        try {
            Usuario usuarioAtua = usuarioService.buscarPorId(usuarioId);

            usuarioDesassembler.copyToDomainObject(usuarioAltInput, usuarioAtua);
            usuarioAtua = usuarioService.salvar(usuarioAtua);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioModelAssembler.toModell(usuarioAtua));
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
