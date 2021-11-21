package com.bitvavo.trader.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.bitvavo.trader.controllers.dtos.MarketDto;
import com.bitvavo.trader.model.Market;

@Mapper(componentModel = "spring")
public interface MarketDtoMapper {
    List<MarketDto> map(List<Market> markets);
}
