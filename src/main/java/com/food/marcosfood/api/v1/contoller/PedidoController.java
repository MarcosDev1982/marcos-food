package com.food.marcosfood.api.v1.contoller;

import com.food.marcosfood.api.v1.assembler.PedidoAssembler;
import com.food.marcosfood.api.v1.assembler.PedidoInputDesassembler;
import com.food.marcosfood.api.v1.assembler.PedidoResumoAssembler;
import com.food.marcosfood.api.v1.model.PedidoDTO;
import com.food.marcosfood.api.v1.model.PedidoResumoDTO;
import com.food.marcosfood.api.v1.model.input.PedidoInput;
import com.food.marcosfood.core.data.PageableTranslator;
import com.food.marcosfood.core.security.CheckSecurity;
import com.food.marcosfood.core.security.MarcosSecurity;
import com.food.marcosfood.domain.exception.EntidadeNaoEncotrada;
import com.food.marcosfood.domain.exception.NegocioExcepetion;
import com.food.marcosfood.domain.fiter.PedidoFilter;
import com.food.marcosfood.domain.model.Pedido;
import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.domain.repository.PedidoRepository;
import com.food.marcosfood.domain.service.PedidoService;
import com.food.marcosfood.infrastructure.respository.spec.PedidoSpecs;
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
@RequestMapping("/v1/pedidos")
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

    @Autowired
    private MarcosSecurity marcosSecurity;

    @CheckSecurity.Pedidos.PodePesquisar
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
    @CheckSecurity.Pedidos.PodePesquisar

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscaPedidoPorId(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarPorId(codigoPedido);
        return pedidoAssembler.toDto(pedido);
    }

    @CheckSecurity.Pedidos.PodeCriar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastraPedido(@RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novopedido = pedidoInputDesassembler.toDomainObject(pedidoInput);
            novopedido.setCliente(new Usuario());
            novopedido.getCliente().setId(marcosSecurity.getUsuarioId());
            novopedido = pedidoService.cadastraPedido(novopedido);
            return pedidoAssembler.toDto(novopedido);
        } catch (EntidadeNaoEncotrada e) {
            throw new NegocioExcepetion(e.getMessage(), e);
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
