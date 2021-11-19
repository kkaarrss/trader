package com.bitvavo.trader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bitvavo.api.Bitvavo;
import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.model.Run;
import com.bitvavo.trader.repository.MarketRepository;
import com.bitvavo.trader.repository.PriceRepository;
import com.bitvavo.trader.repository.RunRepository;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
@Slf4j
public class Ticker {
    private RunRepository runRepository;
    private PriceRepository priceRepository;
    private CandlesCalculator candlesCalculator;
    private MarketRepository marketRepository;

    public Ticker(RunRepository runRepository, PriceRepository priceRepository, CandlesCalculator candlesCalculator, MarketRepository marketRepository) {
        this.runRepository = runRepository;
        this.candlesCalculator = candlesCalculator;
        this.marketRepository = marketRepository;
        this.priceRepository = priceRepository;
    }

    private final Bitvavo bitvavo = new Bitvavo(new JSONObject("{" +
            "APIKEY: '6aed39883a6593e8efe78fdb91d8665ec73e0614a85a98688f225d59e38d3e64', " +
            "APISECRET: '8b19bda51d1bbdbe36befab0e265714e0efa045ceef65f7d68b894a636dedb2a705c4b806efb2ed0ef339c93e8ca37bba0a6f82e93fc41a26b5eceae8289ab70', " +
            "RESTURL: 'https://api.bitvavo.com/v2'," +
            "WSURL: 'wss://ws.bitvavo.com/v2/'," +
            "ACCESSWINDOW: 10000, " +
            "DEBUGGING: false }"));


    @Scheduled(cron = "*/10 * * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", lockAtMostFor = "PT8S")
    void tick(){
        Long time = java.time.Instant.now().getEpochSecond();

        Run run = Run.builder()
                .time(time)
                .prices(new HashSet<>())
                .build();

        // options: market
        JSONArray response = bitvavo.tickerPrice(new JSONObject());

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

    @Async
    void getCandles(String market) {
        JSONObject properties = new JSONObject();
        properties.put("limit", 4);

        //String[] intervals = new String[] { "1m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d" };
        String[] intervals = new String[] {"1m", "5m"};
        for(int i = 0; i < intervals.length; i ++) {
            JSONArray candles = bitvavo.candles(market, intervals[i], properties);
            if (candles != null && candles instanceof JSONArray) {
                for (int y = 0; y < candles.length(); y++) {
                    JSONArray candle = candles.getJSONArray(y);
                    if (candle != null && candle instanceof JSONArray) {
                        log.info("market: {}, interval {}, {}", market, intervals[i], candle);

                        Double open = Double.valueOf(candle.get(1).toString());
                        Double high = Double.valueOf(candle.get(2).toString());
                        Double low = Double.valueOf(candle.get(3).toString());
                        Double close = Double.valueOf(candle.get(4).toString());
                        Double volume = Double.valueOf(candle.get(5).toString());
                    }
                }
            }
        }
    }
}
