package com.bitvavo.trader.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitvavo.trader.controllers.mappers.MarketDtoMapper;
import com.bitvavo.trader.repository.MarketRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MarketsController {
    MarketRepository marketRepository;
    MarketDtoMapper marketDtoMapper;

    @GetMapping("/market")
    public MarketsResponse getAllMarkets() {
        return new MarketsResponse(marketDtoMapper.map(marketRepository.findAll()));
    }
}
