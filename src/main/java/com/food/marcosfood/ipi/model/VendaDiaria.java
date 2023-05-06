package com.food.marcosfood.ipi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {

    private Date data;
    private Long totoalVendas;
    private BigDecimal totalFaturado;

}
