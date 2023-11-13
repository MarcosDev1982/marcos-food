package com.food.marcosfood.api.contoller;

import com.food.marcosfood.core.data.PageableTranslator;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.repository.PedidoRepository;
import com.food.marcosfood.domain.fiter.PedidoFilter;
import com.food.marcosfood.domain.service.PedidoService;
import com.food.marcosfood.infrastructure.respository.spec.PedidoSpecs;
import com.food.marcosfood.api.model.input.assembler.PedidoAssembler;
import com.food.marcosfood.api.model.input.assembler.PedidoInputDesassembler;
import com.food.marcosfood.api.model.input.assembler.PedidoResumoAssembler;
import com.food.marcosfood.api.model.PedidoDTO;
import com.food.marcosfood.api.model.PedidoResumoDTO;
import com.food.marcosfood.api.model.input.PedidoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoAssembler pedidoAssembler;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoResumoAssembler pedidoResumoAssembler;
    @Autowired
    private PedidoInputDesassembler pedidoInputDesassembler;

    @Autowired
    private PedidoRepository pedidoRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);
        Page<Pedido> pedidoPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(pedidoFilter), pageable);
        List<PedidoResumoDTO> pedidoResumoDTOList = pedidoResumoAssembler.toCollectonDto(pedidoPage.getContent());
        Page<PedidoResumoDTO> pedidoResumoDTOPage = new PageImpl<>(pedidoResumoDTOList, pageable, pedidoPage.getTotalElements());
        return pedidoResumoDTOPage;
    }
/*    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public MappingJacksonValue buscaTodos(@RequestParam(required = false) String campos) {

        List<Pedido> pedidoList = pedidoService.pedidoList();
        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoResumoAssembler.toCollectonDto(pedidoList);

        MappingJacksonValue pedidosMappingJacksonValue = new MappingJacksonValue(pedidoResumoDTOS);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
        if (StringUtils.isNotBlank(campos)) {
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }
        pedidosMappingJacksonValue.setFilters(filterProvider);

        return pedidosMappingJacksonValue;

    }*/


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscaPedidoPorId(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarPorId(codigoPedido);
        return pedidoAssembler.toDto(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastraPedido(@RequestBody PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoService.cadastraPedido(pedidoInputDesassembler.toDomainObject(pedidoInput));
            return pedidoAssembler.toDto(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Pageable traduzirPageable(Pageable pageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restauranteNome", "restaurante.nome",
                "valorTotal", "valorTotal",
                "nomeCliente", "cliente.nome"
        );
        return PageableTranslator.translate(pageable, mapeamento);
    }



}
