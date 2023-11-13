package com.food.marcosfood.api.contoller;

import com.food.marcosfood.domain.model.Produto;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.repository.ProdutoRepository;
import com.food.marcosfood.domain.service.ProdutoService;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.api.model.input.assembler.ProdutoAssembler;
import com.food.marcosfood.api.model.input.assembler.ProdutoDesassembler;
import com.food.marcosfood.api.model.ProdutoDTO;
import com.food.marcosfood.api.model.input.ProdutoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurante/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoAssembler produtoAssembler;

    @Autowired
    private ProdutoDesassembler produtoDesassembler;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoDTO> buscarTodos(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativo) {
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
        List<Produto> todosProdutos= null;

        if (incluirInativo){
          produtoRepository.findTodosByRestaurante(restaurante);
        }else{
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

         return produtoAssembler.toCollectionModel(todosProdutos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastarProduto(@RequestBody @Valid ProdutoInput produtoInput, @PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarPorId(restauranteId);

        Produto produto = produtoDesassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.inserirProduto(produto);

        return produtoAssembler.toModel(produto);

    }

    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscaPorId(restauranteId, produtoId);

        produtoDesassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = produtoService.inserirProduto(produtoAtual);

        return produtoAssembler.toModel(produtoAtual);

    }


}
