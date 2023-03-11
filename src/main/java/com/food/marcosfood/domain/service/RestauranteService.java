package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.EntidadeEmUsoException;
import com.food.marcosfood.domain.exception.RestauranteNaoEncontadaException;
import com.food.marcosfood.domain.model.*;
import com.food.marcosfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    private static final String RESTAURANTE_NAO_ENCONTRADO = "O codigo %d não pode ser encontrado";
    private static final String NAO_EXISTE_COZINHA_COM_CODIGO = "Não existe Cozinha com codigo %d";
    private static final String ESTADO_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO = "Estado de codigo %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private FormadePagmentoService FormadePagmentoService;


    @Autowired
    private UsuarioService usuarioService;

    public List<Restaurante> buscarTodos() {

        return restauranteRepository.findAll();

    }


    public Restaurante buscarPorId(Long retuaranteId) {


        return restauranteRepository.findById(retuaranteId).orElseThrow(
                () -> new RestauranteNaoEncontadaException(retuaranteId));


    }

    @Transactional
    public Restaurante inserir(Restaurante restaurante) {

        Long cozinhdaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarPorId(cozinhdaId);
        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cidadeService.findByIdCidade(cidadeId);
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);

    }

    public Restaurante updadte(Long restauranteId, Restaurante restaurante) {
        if (!restauranteRepository.existsById(restauranteId)) {
            throw new RestauranteNaoEncontadaException(restauranteId);
        }
        restaurante.setId(restauranteId);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void delete(Long restuaranteId) {
        try {

            restauranteRepository.deleteById(restuaranteId);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontadaException(restuaranteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(ESTADO_DE_CODIGO_NAO_PODE_SER_REMOVIDA_POIS_ESTA_EM_USO, restuaranteId));
        }
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restuaranteAtual = buscarPorId(restauranteId);
        restuaranteAtual.ativar();


    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restuaranteAtual = buscarPorId(restauranteId);
        restuaranteAtual.inativar();
    }

    @Transactional
    public void desassociaFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarPorId(restauranteId);
        FormaPagamento formaPagamento = FormadePagmentoService.findById(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);

    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarPorId(restauranteId);
        FormaPagamento formaPagamento = FormadePagmentoService.findById(formaPagamentoId);
        restaurante.adcionarFormaPagamento(formaPagamento);

    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = buscarPorId(restauranteId);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = buscarPorId(restauranteId);
        restaurante.fefhar();
    }

    public void asscociarUsuario(Long restuarnteId, Long usuarioId) {
        Restaurante restaurante = buscarPorId(restuarnteId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        restaurante.adicionarResponsavel(usuario);
    }

    public void desasscociarUsuario(Long restuarnteId, Long usuarioId) {
        Restaurante restaurante = buscarPorId(restuarnteId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        restaurante.removerResponsavel(usuario);
    }

    @Transactional
    public void ativar(List<Long> restuarnteIds) {
        restuarnteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativar(List<Long> restuarnteIds) {
        restuarnteIds.forEach(this::inativar);
    }


}
