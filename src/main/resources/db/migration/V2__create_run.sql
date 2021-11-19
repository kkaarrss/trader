create table run
(   id      BIGSERIAL NOT NULL
,   time    bigint NOT NULL
,   primary key (id));

drop table price;

create table price
(   run_id  bigint
,   market  varchar(10) NOT NULL
,   price  numeric(30,15)
,   primary key (run_id, market)
);
