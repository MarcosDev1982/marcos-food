package com.food.marcosfood.api.v1.contoller;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.domain.service.VendaQuerYService;
import com.food.marcosfood.domain.service.VendaReportService;
import com.food.marcosfood.api.v1.model.VendaDiaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQuerYService vendaQuerYService;

    @Autowired
    private VendaReportService vendaReportService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultaVendaDiaria(VendasDiariasFilter filter, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQuerYService.consultarVendasDiarias(filter, timeOffset);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultaVendaDiariaPdf(VendasDiariasFilter filter, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filter, timeOffset);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
    }

    public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
    }
}
