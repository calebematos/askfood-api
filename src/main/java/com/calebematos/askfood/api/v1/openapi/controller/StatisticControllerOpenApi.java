package com.calebematos.askfood.api.v1.openapi.controller;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.dto.DailySale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticControllerOpenApi {

    List<DailySale> consultDailySales(DailySaleFilter filter, String timeOffset);

    ResponseEntity<byte[]> consultDailySalesPdf(DailySaleFilter filter, String timeOffset);
}
