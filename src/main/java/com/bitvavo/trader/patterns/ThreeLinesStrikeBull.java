package com.bitvavo.trader.patterns;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.model.Candle;
import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.repository.CandleRepository;
import com.bitvavo.trader.repository.PriceRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ThreeLinesStrikeBull implements PatternProcessor {
    private CandleRepository candleRepository;
    private PriceRepository priceRepository;

    @Async
    @Override
    public void run(final Market market, final long time) {
        List<Candle> candles = candleRepository.findTop4CandlesByMarketOrderByStartDesc(market);

        if (candles.size() == 4 &&
            candles.get(0).getOpen() < candles.get(0).getClose() &&
            candles.get(1).getOpen() > candles.get(1).getClose() &&
            candles.get(2).getOpen() > candles.get(2).getClose() &&
            candles.get(3).getOpen() > candles.get(3).getClose() &&
            candles.get(0).getClose() > candles.get(3).getOpen()
        ) {
            log.warn("1m ThreeLinesStrikeBull {} {}", market.getName(), candles.get(0).getClose());
            candles.forEach(c -> log.info("{}", c.toString()));

            try {
                Thread.sleep(180000);
            } catch (Exception e) {

            }

            Price price = priceRepository.findTop1ByMarketOrderByRunDesc(market);

            log.info("{} is now {} from {}", market.getName(), price.getPrice(), candles.get(0).getClose());
        }

    }
}
