package com.bitvavo.trader.patterns;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.repository.MarketRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PatternMatcher {
    private List<PatternProcessor> processors;
    private MarketRepository marketRepository;

    @Async
    public void run(long time) {
        List<Market> markets = marketRepository.findAll();
        markets.stream().forEach(
                market -> {
                    processors.forEach(processor -> processor.run(market, time));
                }
        );

    }
}
