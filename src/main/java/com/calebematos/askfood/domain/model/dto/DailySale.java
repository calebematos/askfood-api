package com.calebematos.askfood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DailySale {

    private Date date;
    private Long salesAmount;
    private BigDecimal totalBilled;

}
