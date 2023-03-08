package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.filter.DailySaleFilter;
import com.calebematos.askfood.domain.model.dto.DailySale;

import java.util.List;

public interface SalesQueryService {

    List<DailySale> consultDailySales(DailySaleFilter filter);

}
