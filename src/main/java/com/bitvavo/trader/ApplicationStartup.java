package com.bitvavo.trader;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bitvavo.trader.repository.CandleRepository;
import com.bitvavo.trader.repository.PriceRepository;
import com.bitvavo.trader.repository.RunRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApplicationStartup {
    private CandleRepository candleRepository;
    private PriceRepository priceRepository;
    private RunRepository runRepository;

    @PostConstruct
    public void onApplicationEvent() {
//        candleRepository.deleteAll();
//        priceRepository.deleteAll();
//        runRepository.deleteAll();
    }
}
