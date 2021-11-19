package com.bitvavo.trader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitvavo.trader.model.Market;

@Repository
public interface MarketRepository extends CrudRepository <Market, Long> {
    List<Market> findAll();
}
