package com.food.marcosfood.domain.service;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendasDiariasFilter filter, String timeOffset);
}
