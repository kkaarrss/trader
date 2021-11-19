create table candle
(   market  varchar(10) NOT NULL
,   start   bigint not null
,   stop    bigint not null
,   open  numeric(30,15)
,   high  numeric(30,15)
,   low  numeric(30,15)
,   close  numeric(30,15)
,   primary key (market, start, stop)
);
