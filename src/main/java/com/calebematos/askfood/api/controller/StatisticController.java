package com.calebematos.askfood.api.controller;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.dto.DailySale;
import com.calebematos.askfood.domain.service.SalesQueryService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/daily-sales")
    public List<DailySale> consultDailySales(DailySaleFilter filter,
                      @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return salesQueryService.consultDailySales(filter, timeOffset);
    }


}
