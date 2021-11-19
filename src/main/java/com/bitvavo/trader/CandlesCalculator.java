package com.bitvavo.trader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.model.Candle;
import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.model.Run;
import com.bitvavo.trader.patterns.PatternMatcher;
import com.bitvavo.trader.repository.CandleRepository;
import com.bitvavo.trader.repository.RunRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class CandlesCalculator {
    private CandleRepository candleRepository;
    private RunRepository runRepository;

    @Async
    public void calculateCandles(long time) {
        List<Run> runs = runRepository.findRunsByTimeIsAfterOrderByIdAsc(time-70L);
        if (runs.size() < 7) return;
        Map<Long, Candle> candlesMap = new HashMap<>();
        for (Run run: runs) {
            for (Price price: run.getPrices()){
                Candle candle = candlesMap.get(price.getMarket().getId());
                if (candle == null){
                    candle = Candle.builder()
                            .market(price.getMarket())
                            .start(run.getTime())
                            .high(price.getPrice())
                            .low(price.getPrice())
                            .open(price.getPrice())
                            .build();

                    candlesMap.put(price.getMarket().getId(), candle);
                } else {
                    candle.setStop(run.getTime());
                    candle.setClose(price.getPrice());
                    if (candle.getHigh() < price.getPrice()) {
                        candle.setHigh(price.getPrice());
                    }
                    if (candle.getLow() > price.getPrice()) {
                        candle.setLow(price.getPrice());
                    }
                }
            }

        }

        candleRepository.saveAll(candlesMap.values());
    }

}
