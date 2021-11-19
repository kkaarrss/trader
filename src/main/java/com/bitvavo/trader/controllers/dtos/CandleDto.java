package com.bitvavo.trader.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CandleDto {
    private String market;
    private long start;
    private long stop;
    private double open;
    private double high;
    private double low;
    private double close;
}
