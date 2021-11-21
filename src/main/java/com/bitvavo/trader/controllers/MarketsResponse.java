package com.bitvavo.trader.controllers;

import java.util.List;

import com.bitvavo.trader.controllers.dtos.MarketDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarketsResponse {
    List<MarketDto> markets;
}
