package com.food.marcosfood.domain.service;


import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.exception.UsuarioNaoEncontadoException;
import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private static final String USUARIO_DE_CÓDIGO_NAO_PODE_SER_REMOVIDO = "Usuário de código %d não pode ser remivdo pois está em uso";

    private static final String USUARIO_JA_COM_EMAIL = "Já existe um usuário c cadastrado com email %s";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }


    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioNaoEncontadoException(usuarioId)
        );
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
           entityManager.detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioExcepetion(String.format(USUARIO_JA_COM_EMAIL, usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleta(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontadoException(usuarioId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(USUARIO_DE_CÓDIGO_NAO_PODE_SER_REMOVIDO, usuarioId));
        }
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarPorId(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioExcepetion("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

}
