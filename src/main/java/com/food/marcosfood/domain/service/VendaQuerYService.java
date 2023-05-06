package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.ipi.model.VendaDiaria;
import org.springframework.stereotype.Service;

import java.util.List;
public interface VendaQuerYService {

    List<VendaDiaria> consultarVendasDiarias(VendasDiariasFilter filter);

}
