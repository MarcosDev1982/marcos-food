package com.food.marcosfood.ipi.contoller;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.domain.service.VendaQuerYService;
import com.food.marcosfood.infrastructure.service.VendaQueryServiceImpl;
import com.food.marcosfood.ipi.model.VendaDiaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQuerYService vendaQuerYService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultaVendaDiaria(VendasDiariasFilter filter, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
     return vendaQuerYService.consultarVendasDiarias(filter, timeOffset);
    }
}
