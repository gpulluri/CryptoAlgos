package com.okex.futures.models;

import java.util.Date;

public class OrderUpdate {
    private Date lastFillTime;
    private Double filledQty;
    private Double fee;
    private Double priceAvg;
    private String type;
    private String clientOid;
    private Double lastFillqty;
    private String instrumentId;
    private Double lastFillpx;
    private Double pnl;
    private Integer size;
    private String lastAmendResult;
    private Double price;
    private String lastFillId;
    private String errorCode;
    private String state;
    private String contractVal;
    private String orderId;

    public Date getLastFillTime() {
        return lastFillTime;
    }

    public void setLastFillTime(Date lastFillTime) {
        this.lastFillTime = lastFillTime;
    }

    public Double getFilledQty() {
        return filledQty;
    }

    public void setFilledQty(Double filledQty) {
        this.filledQty = filledQty;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getPriceAvg() {
        return priceAvg;
    }

    public void setPriceAvg(Double priceAvg) {
        this.priceAvg = priceAvg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public Double getLastFillqty() {
        return lastFillqty;
    }

    public void setLastFillqty(Double lastFillqty) {
        this.lastFillqty = lastFillqty;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Double getLastFillpx() {
        return lastFillpx;
    }

    public void setLastFillpx(Double lastFillpx) {
        this.lastFillpx = lastFillpx;
    }

    public Double getPnl() {
        return pnl;
    }

    public void setPnl(Double pnl) {
        this.pnl = pnl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLastAmendResult() {
        return lastAmendResult;
    }

    public void setLastAmendResult(String lastAmendResult) {
        this.lastAmendResult = lastAmendResult;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLastFillId() {
        return lastFillId;
    }

    public void setLastFillId(String lastFillId) {
        this.lastFillId = lastFillId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContractVal() {
        return contractVal;
    }

    public void setContractVal(String contractVal) {
        this.contractVal = contractVal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Instrument"+instrumentId+", Fill Qty: "+lastFillqty+", Type :"+type+" , Price : "+price;
    }
}
