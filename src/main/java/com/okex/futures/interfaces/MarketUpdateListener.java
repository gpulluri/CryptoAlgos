package com.okex.futures.interfaces;


import com.okex.futures.models.FuturesPrice;
import com.okex.futures.models.OrderUpdate;

public interface MarketUpdateListener {
    void onPriceUpdate(FuturesPrice px);
    void onOrderUpdate(OrderUpdate result);
}
