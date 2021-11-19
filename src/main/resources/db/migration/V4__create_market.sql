create table market
(   id      serial primary key
,   name  varchar(10)
);

drop table price;

create table price
(   run_id  bigint
,   market_id  int
,   price  numeric(30,15)
,   primary key (run_id, market_id)
);

drop table candle;

create table candle
(   market_id  int NOT NULL
,   start   bigint not null
,   stop    bigint not null
,   open  numeric(30,15)
,   high  numeric(30,15)
,   low  numeric(30,15)
,   close  numeric(30,15)
,   primary key (market_id, start, stop)
);

delete from run where 1 = 1;

INSERT INTO market (name) VALUES ('SOL-EUR');
INSERT INTO market (name) VALUES ('LSK-EUR');
INSERT INTO market (name) VALUES ('GNT-EUR');
INSERT INTO market (name) VALUES ('TRX-EUR');
INSERT INTO market (name) VALUES ('XTZ-EUR');
INSERT INTO market (name) VALUES ('HOT-EUR');
INSERT INTO market (name) VALUES ('FTM-EUR');
INSERT INTO market (name) VALUES ('SHIB-EUR');
INSERT INTO market (name) VALUES ('ANKR-EUR');
INSERT INTO market (name) VALUES ('NULS-EUR');
INSERT INTO market (name) VALUES ('AION-EUR');
INSERT INTO market (name) VALUES ('BAND-EUR');
INSERT INTO market (name) VALUES ('OXT-EUR');
INSERT INTO market (name) VALUES ('DIA-EUR');
INSERT INTO market (name) VALUES ('VTHO-EUR');
INSERT INTO market (name) VALUES ('NANO-EUR');
INSERT INTO market (name) VALUES ('BCH-EUR');
INSERT INTO market (name) VALUES ('VGX-EUR');
INSERT INTO market (name) VALUES ('LPT-EUR');
INSERT INTO market (name) VALUES ('OMG-EUR');
INSERT INTO market (name) VALUES ('STORM-EUR');
INSERT INTO market (name) VALUES ('AXS-EUR');
INSERT INTO market (name) VALUES ('NKN-EUR');
INSERT INTO market (name) VALUES ('STRAX-EUR');
INSERT INTO market (name) VALUES ('XEM-EUR');
INSERT INTO market (name) VALUES ('SLP-EUR');
INSERT INTO market (name) VALUES ('ELF-EUR');
INSERT INTO market (name) VALUES ('CVC-EUR');
INSERT INTO market (name) VALUES ('XRP-EUR');
INSERT INTO market (name) VALUES ('POND-EUR');
INSERT INTO market (name) VALUES ('BAL-EUR');
INSERT INTO market (name) VALUES ('YFII-EUR');
INSERT INTO market (name) VALUES ('OCEAN-EUR');
INSERT INTO market (name) VALUES ('LTC-EUR');
INSERT INTO market (name) VALUES ('UNI-EUR');
INSERT INTO market (name) VALUES ('DAI-EUR');
INSERT INTO market (name) VALUES ('REEF-EUR');
INSERT INTO market (name) VALUES ('PHA-EUR');
INSERT INTO market (name) VALUES ('DGB-EUR');
INSERT INTO market (name) VALUES ('DOT-EUR');
INSERT INTO market (name) VALUES ('IOST-EUR');
INSERT INTO market (name) VALUES ('ATA-EUR');
INSERT INTO market (name) VALUES ('CHZ-EUR');
INSERT INTO market (name) VALUES ('JST-EUR');
INSERT INTO market (name) VALUES ('MASK-EUR');
INSERT INTO market (name) VALUES ('RSR-EUR');
INSERT INTO market (name) VALUES ('WIN-EUR');
INSERT INTO market (name) VALUES ('STORJ-EUR');
INSERT INTO market (name) VALUES ('SRM-EUR');
INSERT INTO market (name) VALUES ('STEEM-EUR');
INSERT INTO market (name) VALUES ('USDC-EUR');
INSERT INTO market (name) VALUES ('YFI-EUR');
INSERT INTO market (name) VALUES ('CHR-EUR');
INSERT INTO market (name) VALUES ('NPXS-EUR');
INSERT INTO market (name) VALUES ('ENJ-EUR');
INSERT INTO market (name) VALUES ('MATIC-EUR');
INSERT INTO market (name) VALUES ('SNT-EUR');
INSERT INTO market (name) VALUES ('FORTH-EUR');
INSERT INTO market (name) VALUES ('UTK-EUR');
INSERT INTO market (name) VALUES ('OGN-EUR');
INSERT INTO market (name) VALUES ('RLY-EUR');
INSERT INTO market (name) VALUES ('SKL-EUR');
INSERT INTO market (name) VALUES ('ZRX-EUR');
INSERT INTO market (name) VALUES ('GLM-EUR');
INSERT INTO market (name) VALUES ('KSM-EUR');
INSERT INTO market (name) VALUES ('AKRO-EUR');
INSERT INTO market (name) VALUES ('AUDIO-EUR');
INSERT INTO market (name) VALUES ('RDD-EUR');
INSERT INTO market (name) VALUES ('POWR-EUR');
INSERT INTO market (name) VALUES ('REN-EUR');
INSERT INTO market (name) VALUES ('BTC-EUR');
INSERT INTO market (name) VALUES ('MIOTA-EUR');
INSERT INTO market (name) VALUES ('NEO-EUR');
INSERT INTO market (name) VALUES ('CTSI-EUR');
INSERT INTO market (name) VALUES ('TOMO-EUR');
INSERT INTO market (name) VALUES ('KEEP-EUR');
INSERT INTO market (name) VALUES ('ONG-EUR');
INSERT INTO market (name) VALUES ('COTI-EUR');
INSERT INTO market (name) VALUES ('GNO-EUR');
INSERT INTO market (name) VALUES ('NMR-EUR');
INSERT INTO market (name) VALUES ('AAVE-EUR');
INSERT INTO market (name) VALUES ('ADA-EUR');
INSERT INTO market (name) VALUES ('LOOM-EUR');
INSERT INTO market (name) VALUES ('POLY-EUR');
INSERT INTO market (name) VALUES ('KMD-EUR');
INSERT INTO market (name) VALUES ('XVG-EUR');
INSERT INTO market (name) VALUES ('BNB-EUR');
INSERT INTO market (name) VALUES ('GAS-EUR');
INSERT INTO market (name) VALUES ('AE-EUR');
INSERT INTO market (name) VALUES ('SNX-EUR');
INSERT INTO market (name) VALUES ('STRAT-EUR');
INSERT INTO market (name) VALUES ('MTL-EUR');
INSERT INTO market (name) VALUES ('MIR-EUR');
INSERT INTO market (name) VALUES ('FIL-EUR');
INSERT INTO market (name) VALUES ('ALICE-EUR');
INSERT INTO market (name) VALUES ('FTT-EUR');
INSERT INTO market (name) VALUES ('PUNDIX-EUR');
INSERT INTO market (name) VALUES ('SUPER-EUR');
INSERT INTO market (name) VALUES ('WTC-EUR');
INSERT INTO market (name) VALUES ('ALGO-EUR');
INSERT INTO market (name) VALUES ('BAT-EUR');
INSERT INTO market (name) VALUES ('QNT-EUR');
INSERT INTO market (name) VALUES ('AR-EUR');
INSERT INTO market (name) VALUES ('INJ-EUR');
INSERT INTO market (name) VALUES ('CMT-EUR');
INSERT INTO market (name) VALUES ('LINK-EUR');
INSERT INTO market (name) VALUES ('ICX-EUR');
INSERT INTO market (name) VALUES ('CAKE-EUR');
INSERT INTO market (name) VALUES ('REP-EUR');
INSERT INTO market (name) VALUES ('AVAX-EUR');
INSERT INTO market (name) VALUES ('QTUM-EUR');
INSERT INTO market (name) VALUES ('CLV-EUR');
INSERT INTO market (name) VALUES ('DATA-EUR');
INSERT INTO market (name) VALUES ('LINA-EUR');
INSERT INTO market (name) VALUES ('MKR-EUR');
INSERT INTO market (name) VALUES ('STMX-EUR');
INSERT INTO market (name) VALUES ('ANT-EUR');
INSERT INTO market (name) VALUES ('UMA-EUR');
INSERT INTO market (name) VALUES ('SUSHI-EUR');
INSERT INTO market (name) VALUES ('RLC-EUR');
INSERT INTO market (name) VALUES ('CRV-EUR');
INSERT INTO market (name) VALUES ('LRC-EUR');
INSERT INTO market (name) VALUES ('RVN-EUR');
INSERT INTO market (name) VALUES ('VTC-EUR');
INSERT INTO market (name) VALUES ('LIT-EUR');
INSERT INTO market (name) VALUES ('SAND-EUR');
INSERT INTO market (name) VALUES ('BOND-EUR');
INSERT INTO market (name) VALUES ('ALPHA-EUR');
INSERT INTO market (name) VALUES ('COMP-EUR');
INSERT INTO market (name) VALUES ('ONT-EUR');
INSERT INTO market (name) VALUES ('XLM-EUR');
INSERT INTO market (name) VALUES ('MLN-EUR');
INSERT INTO market (name) VALUES ('ADX-EUR');
INSERT INTO market (name) VALUES ('LTO-EUR');
INSERT INTO market (name) VALUES ('PERP-EUR');
INSERT INTO market (name) VALUES ('DENT-EUR');
INSERT INTO market (name) VALUES ('GRT-EUR');
INSERT INTO market (name) VALUES ('BNT-EUR');
INSERT INTO market (name) VALUES ('BSV-EUR');
INSERT INTO market (name) VALUES ('DOGE-EUR');
INSERT INTO market (name) VALUES ('ZIL-EUR');
INSERT INTO market (name) VALUES ('FET-EUR');
INSERT INTO market (name) VALUES ('CELR-EUR');
INSERT INTO market (name) VALUES ('ETH-EUR');
INSERT INTO market (name) VALUES ('WAVES-EUR');
INSERT INTO market (name) VALUES ('KNC-EUR');
INSERT INTO market (name) VALUES ('ETC-EUR');
INSERT INTO market (name) VALUES ('SXP-EUR');
INSERT INTO market (name) VALUES ('VET-EUR');
INSERT INTO market (name) VALUES ('NU-EUR');
INSERT INTO market (name) VALUES ('ROSE-EUR');
INSERT INTO market (name) VALUES ('1INCH-EUR');
INSERT INTO market (name) VALUES ('GHST-EUR');
INSERT INTO market (name) VALUES ('DNT-EUR');
INSERT INTO market (name) VALUES ('HNT-EUR');
INSERT INTO market (name) VALUES ('EOS-EUR');
INSERT INTO market (name) VALUES ('MANA-EUR');
INSERT INTO market (name) VALUES ('REQ-EUR');
INSERT INTO market (name) VALUES ('USDT-EUR');
INSERT INTO market (name) VALUES ('ARK-EUR');
INSERT INTO market (name) VALUES ('TRB-EUR');
INSERT INTO market (name) VALUES ('NAS-EUR');
INSERT INTO market (name) VALUES ('DCR-EUR');

