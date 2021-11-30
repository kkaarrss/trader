package com.bitvavo.trader;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.clients.BitvavoClient;
import com.bitvavo.trader.model.Candle;
import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.repository.CandleRepository;
import com.bitvavo.trader.repository.MarketRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class CandlesCalculator {
    private CandleRepository candleRepository;
    private MarketRepository marketRepository;
    private BitvavoClient bitvavoClient;

    @Async
    public void calculateCandles(long time) {
        List<Market> markets = marketRepository.findAll();
        List<Candle> candles = markets.parallelStream()
                .map(market -> bitvavoClient.getCandle(market, "1m", time))
                .filter(candle -> candle != null)
                .collect(Collectors.toList());

        candleRepository.saveAll(candles);
    }

}
