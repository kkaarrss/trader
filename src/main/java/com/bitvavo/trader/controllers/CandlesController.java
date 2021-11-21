package com.bitvavo.trader.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bitvavo.trader.controllers.mappers.CandlesDtoMapper;
import com.bitvavo.trader.repository.CandleRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CandlesController {
    private CandleRepository candleRepository;
    private CandlesDtoMapper candlesDtoMapper;

    @GetMapping("/market/{market}/candles/{interval}")
    public CandlesResponse getCandles(@PathVariable("market") String market, @PathVariable("interval") int interval) {
        return new CandlesResponse(candlesDtoMapper.map(candleRepository.findCandlesQuery(market, interval)));
    }
}
