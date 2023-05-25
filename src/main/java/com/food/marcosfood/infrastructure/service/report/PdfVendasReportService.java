package com.food.marcosfood.infrastructure.service.report;

import com.food.marcosfood.domain.fiter.VendasDiariasFilter;
import com.food.marcosfood.domain.service.VendaQuerYService;
import com.food.marcosfood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfVendasReportService implements VendaReportService {



    @Autowired
    private VendaQuerYService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendasDiariasFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/relatorios/vendas-diarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }
}
