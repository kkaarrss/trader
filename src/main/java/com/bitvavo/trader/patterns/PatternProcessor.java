package com.bitvavo.trader.patterns;

import org.springframework.scheduling.annotation.Async;

import com.bitvavo.trader.model.Market;

public interface PatternProcessor {
    @Async
    void run(Market market, long time);
}
