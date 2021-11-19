package com.bitvavo.trader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitvavo.trader.model.Market;
import com.bitvavo.trader.model.Price;
import com.bitvavo.trader.model.PriceId;
import com.bitvavo.trader.model.Run;

@Repository
public interface PriceRepository extends CrudRepository<Price, PriceId> {
    List<Price> findAllByRunOrderByMarket(Run run);

    Price findTop1ByMarketOrderByRunDesc(Market market);
}
