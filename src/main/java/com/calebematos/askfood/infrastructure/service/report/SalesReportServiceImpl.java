package com.calebematos.askfood.infrastructure.service.report;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.dto.DailySale;
import com.calebematos.askfood.domain.service.SalesQueryService;
import com.calebematos.askfood.domain.service.SalesReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesReportServiceImpl implements SalesReportService {

    private final SalesQueryService salesQueryService;

    @Override
    public byte[] issueSalesReport(DailySaleFilter filter, String timeOffset) {
        try {
            List<DailySale> dailySales = salesQueryService.consultDailySales(filter, timeOffset);

            var inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", LocaleContextHolder.getLocale());

            var dataSource = new JRBeanCollectionDataSource(dailySales);

            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw ReportException.of("It was not possible to issue a daily sales report", e);
        }
    }
}
