package com.okex.futures.interfaces;

import com.okcoin.commons.okex.open.api.service.futures.FuturesTradeAPIService;

public interface FuturesOrderManager {
    FuturesTradeAPIService getTradeService();
}
