package com.bitvavo.trader.controllers;

import java.util.List;

import com.bitvavo.trader.controllers.dtos.CandleDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CandlesResponse {
    private List<CandleDto> candles;
}
