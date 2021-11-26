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

    public final static String CANDLESQUERYWITHLIMIT = CANDLESQUERY + " limit :limit";

    public final static String CANDLEBYHIGH = """
            select p.market_id
            ,      min(p.start) as start
            ,      max(p.stop) as stop
            ,      min(p.open) as open
            ,      max(p.high) as high
            ,      min(p.low) as low
            ,      min(p.close) as close
            from   candle p
              join market m on p.market_id = m.id
              join (select sub.market_id, max(sub.high) max from candle sub where sub.start >= :start and sub.stop <= :stop group by sub.market_id) s on s.market_id = p.market_id
            where  m.name = :market
              and    p.start >= :start
              and    p.stop <= :stop
              and    p.high = s.max
            group by p.market_id
            """;
}
