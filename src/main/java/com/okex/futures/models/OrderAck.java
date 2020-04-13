package com.okex.futures.models;

import com.okcoin.commons.okex.open.api.bean.futures.result.OrderResult;

public class OrderAck {

    private String clientOid;
    private String orderId;
    private boolean result;
    private String errorCode;
    private String errorMessage;

    public OrderAck(OrderResult orderResult) {
        this.clientOid = orderResult.getClient_oid();
        this.orderId = orderResult.getOrder_id();
        this.result = orderResult.isResult();
        this.errorCode = orderResult.getError_code();
        this.errorMessage = orderResult.getError_messsage();
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
