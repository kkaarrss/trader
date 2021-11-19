package com.bitvavo.trader.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitvavo.trader.dto.PriceDto;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.model.Run;
import com.bitvavo.trader.repository.PriceRepository;
import com.bitvavo.trader.repository.RunRepository;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PriceController {
    private PriceRepository priceRepository;
    private RunRepository runRepository;

    @GetMapping(value = "/price", produces = "application/json")
    public List<PriceDto> getAll() {
        Run run = runRepository.findFirstByOrderByTimeDesc();

        return priceRepository.findAllByRunOrderByMarket(run).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PriceDto map(Price price) {
        return new PriceDto(price.getMarket().getName(), price.getPrice());
    }
}
