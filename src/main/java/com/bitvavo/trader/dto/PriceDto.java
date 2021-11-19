package com.bitvavo.trader.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PriceDto {
    @JsonProperty
    private String market;
    @JsonProperty
    @JsonSerialize(using=MyDoubleDesirializer.class)
    private Double price;
}
