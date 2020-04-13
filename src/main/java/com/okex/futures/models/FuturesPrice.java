package com.okex.futures.models;

import java.util.Date;

public class FuturesPrice {
    private String instrumentId;
    private Double bestBid;
    private Double bestAsk;
    private Double last;
    private Integer lastQty;
    private Date timestamp;

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Double getBestBid() {
        return bestBid;
    }

    public void setBestBid(Double bestBid) {
        this.bestBid = bestBid;
    }

    public Double getBestAsk() {
        return bestAsk;
    }

    public void setBestAsk(Double bestAsk) {
        this.bestAsk = bestAsk;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Integer getLastQty() {
        return lastQty;
    }

    public void setLastQty(Integer lastQty) {
        this.lastQty = lastQty;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Ticker: "+instrumentId+", Px: "+this.last;
    }
}
