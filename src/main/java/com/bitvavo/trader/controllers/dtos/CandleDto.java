package com.bitvavo.trader.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CandleDto {
    private String market;
    private long start;
    private long stop;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
}
