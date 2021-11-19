package com.bitvavo.trader.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="candle")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@IdClass(CandleId.class)
public class Candle implements Serializable {
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Market market;
    @Id
    private long start;
    @Id
    private long stop;
    private double open;
    private double high;
    private double low;
    private double close;

    @Override
    public String toString() {
        return "Candle{" + "start=" + start + ", stop=" + stop + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + '}';
    }
}
