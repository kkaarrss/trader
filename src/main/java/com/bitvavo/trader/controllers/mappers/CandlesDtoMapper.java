package com.bitvavo.trader.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bitvavo.trader.controllers.dtos.CandleDto;
import com.bitvavo.trader.model.Candle;

@Mapper(componentModel = "spring")
public interface CandlesDtoMapper {
    List<CandleDto> map(List<Candle> candles);

    @Mapping(target = "market", source = "market.name")
    CandleDto map(Candle candle);
}
