package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.model.FotoProduto;
import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.domain.service.CatalagoFotoProdutoService;
import com.food.marcosfood.domain.service.FotoStorageService;
import com.food.marcosfood.domain.service.ProdutoService;
import com.food.marcosfood.api.model.input.assembler.FotoProdutoAssembler;
import com.food.marcosfood.api.model.FotoProdutoDto;
import com.food.marcosfood.api.model.input.FotoProdutoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    FotoProdutoAssembler fotoProdutoAssembler;
    @Autowired
    private CatalagoFotoProdutoService catalagoFotoProdutoService;


    @Autowired
    private FotoStorageService fotoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDto atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = produtoService.buscaPorId(restauranteId, produtoId);
        MultipartFile arquivo = fotoProdutoInput.getArquivo();
        FotoProduto foto = new FotoProduto();

        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());


        FotoProduto fotoProduto = catalagoFotoProdutoService.salvar(foto, arquivo.getInputStream());
        return fotoProdutoAssembler.toModell(fotoProduto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDto buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalagoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoAssembler.toModell(fotoProduto);
    }

    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {

        try {
            FotoProduto fotoProduto = catalagoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMideaType(mediaTypeFoto, mediaTypesAceitas);
            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncotrada e) {
            return ResponseEntity.notFound().build();
        }
    }
    private void verificarCompatibilidadeMideaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
        boolean compativel = mediaTypesAceitas.stream().anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaTypeFoto));


        if (!compativel) {

            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirFotoProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        catalagoFotoProdutoService.deletar(restauranteId, produtoId);

    }

}
