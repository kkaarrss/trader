package com.bitvavo.trader.clients;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bitvavo.api.Bitvavo;
import com.bitvavo.trader.model.Candle;
import com.bitvavo.trader.model.Market;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BitvavoClient {
    private final Bitvavo bitvavo = new Bitvavo(new JSONObject("{" +
            "APIKEY: '6aed39883a6593e8efe78fdb91d8665ec73e0614a85a98688f225d59e38d3e64', " +
            "APISECRET: '8b19bda51d1bbdbe36befab0e265714e0efa045ceef65f7d68b894a636dedb2a705c4b806efb2ed0ef339c93e8ca37bba0a6f82e93fc41a26b5eceae8289ab70', " +
//            "APIKEY: 'ba0e847f4be6a19d2a2ffd56a96c0e3b2e1e3835afef0da500249badb9c1fcf1', " +
//            "APISECRET: '2cd76d0ac5773d7c5f648effa4903fe2b6542e3638abd9d20d2365f12d34aae60cdb80c5a04292de8d9b8b012b34ae4261a076007f278009036a493cf03e5141', " +
            "RESTURL: 'https://api.bitvavo.com/v2'," +
            "WSURL: 'wss://ws.bitvavo.com/v2/'," +
            "ACCESSWINDOW: 10000, " +
            "DEBUGGING: false }"));

    public JSONArray tickerPrice() {
        return  bitvavo.tickerPrice(new JSONObject());
    }

    public Candle getCandle(Market market, String interval, long stop) {
        long start = 0;

        switch (interval) {
            case "1m" -> start = stop - 60;
        }

        List<Candle> candles = getCandles(market, interval, start, stop, 1);
        if (candles.size() >= 1) {
            return getCandles(market, interval, start, stop, 1).get(0);
        }

        return null;
    }

    public List<Candle> getCandles(Market market, String interval, long start, long stop, int limit) {
        JSONObject properties = new JSONObject();
        properties.put("limit", limit);
        properties.put("start", start * 1000);
        properties.put("end", stop * 1000);

        //String[] intervals = new String[] { "1m", "5m", "15m", "30m", "1h", "2h", "4h", "6h", "8h", "12h", "1d" };
        JSONArray candles = bitvavo.candles(market.getName(), interval, properties);
        List<Candle> candlesList = map(market, start, stop, candles);

        return candlesList;
    }

    private List<Candle> map(Market market, long start, long stop, JSONArray candles) {
        List<Candle> candlesList = new LinkedList<>();

        if (candles != null && candles instanceof JSONArray) {
            for (int y = 0; y < candles.length(); y++) {
                JSONArray candleJSONArray = candles.getJSONArray(y);
                if (candleJSONArray != null && candleJSONArray instanceof JSONArray) {
                    Candle candle = mapCandle(market, start, stop, candleJSONArray);
                    candlesList.add(candle);
                }
            }
        }

        return candlesList;
    }

    private Candle mapCandle(Market market, long start, long stop, JSONArray candle) {
        Double open = Double.valueOf(candle.get(1).toString());
        Double high = Double.valueOf(candle.get(2).toString());
        Double low = Double.valueOf(candle.get(3).toString());
        Double close = Double.valueOf(candle.get(4).toString());
        Double volume = Double.valueOf(candle.get(5).toString());

        return new Candle(market, start, stop, open, high, low, close, volume);
    }
}
