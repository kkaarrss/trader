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
                 ,      ss.volume
            from (
                     select g.market_id
                          , min(g.start)  as start
                          , max(g.stop)   as stop
                          , min(g.low)    as low
                          , max(g.high)   as high
                          , sum(g.volume) as volume
                     from (
                              select (row_number() over (order by generate_series desc) - 1) / :minutes AS groep
                               ,     sub2.*
                              from (
                                    select *
                                    from (
                                      select generate_series
                                      from   generate_series((floor(extract(epoch from now()) / 60) * 60)\\:\\:int - ((:minutes * :limit) - 1) * 60, (floor(extract(epoch from now()) / 60) * 60)\\:\\:int, 60)
                                      order by generate_series desc
                                    ) sub
                                      left join candle c on c.stop = sub.generate_series and c.market_id = (select m.id from market m where m.name = :market)
                                ) sub2
                          ) g
                     where g.market_id is not null
                     group by groep, g.market_id
                 ) ss
            order by ss.start desc
            """;

    public final static String CANDLEBYHIGH = """
            select p.market_id
            ,      min(p.start) as start
            ,      max(p.stop) as stop
            ,      min(p.open) as open
            ,      max(p.high) as high
            ,      min(p.low) as low
            ,      min(p.close) as close
            ,      sum(p.volume) as volume
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
