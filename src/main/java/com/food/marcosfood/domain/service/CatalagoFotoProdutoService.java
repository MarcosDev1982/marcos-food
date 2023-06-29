package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.exception.FotoProdutoNaoEncontradoException;
import com.food.marcosfood.domain.model.FotoProduto;
import com.food.marcosfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalagoFotoProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> footoExist = produtoRepository.findFotoById(restauranteId, produtoId);
        if (footoExist.isPresent()) {
            nomeArquivoExistente = footoExist.get().getNomeArquivo();
            produtoRepository.delete(footoExist.get());
        }
        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();
        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeAquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo)
                .build();


        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);


        return foto;


    }

    public FotoProduto buscarOuFalhar(Long restuaranteId, Long produtoId) {
        return produtoRepository.findFotoById(restuaranteId, produtoId).orElseThrow(() -> new FotoProdutoNaoEncontradoException(restuaranteId, produtoId));
    }


    public void deletar(Long restauranteId, Long produtoId) {
        FotoProduto fotoById = buscarOuFalhar(restauranteId, produtoId);
        produtoRepository.delete(fotoById);
        produtoRepository.flush();

        fotoStorageService.remover(fotoById.getNomeArquivo());
    }
}
