package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.dto.DailySale;
import com.calebematos.askfood.domain.service.SalesQueryService;
import com.calebematos.askfood.domain.service.SalesReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final SalesQueryService salesQueryService;
    private final SalesReportService salesReportService;

    @GetMapping(value = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> consultDailySales(DailySaleFilter filter,
                      @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return salesQueryService.consultDailySales(filter, timeOffset);
    }

    @GetMapping(value = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultDailySalesPdf(DailySaleFilter filter,
                      @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        byte[] bytesPdf = salesReportService.issueSalesReport(filter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

}
