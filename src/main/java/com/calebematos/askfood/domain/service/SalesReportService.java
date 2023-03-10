package com.calebematos.askfood.domain.service;

import com.calebematos.askfood.domain.filter.DailySaleFilter;

public interface SalesReportService {

    byte[] issueSalesReport(DailySaleFilter filter, String timeOffset);

}
