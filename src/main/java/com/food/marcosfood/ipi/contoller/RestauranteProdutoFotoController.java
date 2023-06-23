package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.model.FotoProduto;
import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.domain.service.CatalagoFotoProdutoService;
import com.food.marcosfood.domain.service.FotoStorageService;
import com.food.marcosfood.domain.service.ProdutoService;
import com.food.marcosfood.ipi.assembler.FotoProdutoAssembler;
import com.food.marcosfood.ipi.model.FotoProdutoDto;
import com.food.marcosfood.ipi.model.input.FotoProdutoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    private CatalagoFotoProdutoService catalagoFotoProdutoService;



    @Autowired
    FotoProdutoAssembler fotoProdutoAssembler;

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


       FotoProduto  fotoProduto = catalagoFotoProdutoService.salvar(foto, arquivo.getInputStream());
       return fotoProdutoAssembler.toModell(fotoProduto);
    }

}
