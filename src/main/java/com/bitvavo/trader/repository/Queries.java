package com.bitvavo.trader.repository;

public class Queries {
    public final static String CANDLESQUERY = """
            select ss.market_id
            ,      ss.start
            ,      ss.stop
            ,      (select sub.open from candle sub where sub.market_id = ss.market_id and sub.start = ss.start) as open
            ,      ss.high
            ,      ss.low
            ,      (select sub.close from candle sub where sub.market_id = ss.market_id and sub.stop = ss.stop) as close
            from (
                select g.market_id
                , min(g.start) as start
                , max(g.stop)  as stop
                , min(g.low)   as low
                , max(g.high)  as high
                from (
                        select (row_number() over (order by start desc) - 1) / :minutes AS groep
                        ,      c.*
                        from   candle c
                          join market m on m.id = c.market_id
                        where m.name = :market
                ) g
                group by groep, g.market_id
            ) ss
            order by ss.start desc""";
}
