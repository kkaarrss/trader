package com.bitvavo.trader;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.clients.BitvavoClient;
import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.model.Run;
import com.bitvavo.trader.repository.MarketRepository;
import com.bitvavo.trader.repository.PriceRepository;
import com.bitvavo.trader.repository.RunRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
@Slf4j
@AllArgsConstructor
public class Ticker {
    private RunRepository runRepository;
    private PriceRepository priceRepository;
    private CandlesCalculator candlesCalculator;
    private MarketRepository marketRepository;

    private BitvavoClient bitvavoClient;

    @Scheduled(cron = "*/10 * * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", lockAtMostFor = "PT8S")
    void tick(){
        Long time = java.time.Instant.now().getEpochSecond();

        Run run = Run.builder()
                .time(time)
                .prices(new HashSet<>())
                .build();

        // options: market
        JSONArray response = bitvavoClient.tickerPrice();

        List<Market> markets = marketRepository.findAll();
        for(int i = 0; i < response.length(); i ++) {
            String market = response.getJSONObject(i).get("market").toString();

            Optional<Market> dbMarket = markets.stream()
                    .filter(f -> f.getName().equals(market))
                    .findFirst();
            if (dbMarket.isPresent()) {
                Double price = Double.valueOf(response.getJSONObject(i).get("price").toString());

                Price priceModel = Price.builder().run(run).market(dbMarket.get()).price(price).build();

                run.add(priceModel);
            }
        }

        runRepository.save(run);
        priceRepository.saveAll(run.getPrices());

        if (time % 60 < 10) {
            candlesCalculator.calculateCandles(time);
        }
    }
}
