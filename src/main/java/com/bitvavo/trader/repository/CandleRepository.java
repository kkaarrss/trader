package com.bitvavo.trader.repository;

import static com.bitvavo.trader.repository.Queries.CANDLESQUERY;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitvavo.trader.model.Candle;
import com.bitvavo.trader.model.Market;

@Repository
public interface CandleRepository extends CrudRepository<Candle,Long> {
    LinkedList<Candle> findTop4CandlesByMarketOrderByStartDesc(Market market);

    @Query(nativeQuery = true, value = CANDLESQUERY)
    LinkedList<Candle> findCandlesQuery(@Param("market") String Market, @Param("minutes") int minutes);
}
