package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.api.model.VendaDiaria;

import java.util.List;
public interface VendaQuerYService {

    List<VendaDiaria> consultarVendasDiarias(VendasDiariasFilter filter, String timeOffset);

}
