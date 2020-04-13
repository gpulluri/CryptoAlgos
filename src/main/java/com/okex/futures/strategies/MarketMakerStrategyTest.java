package com.okex.futures.strategies;

import com.okex.futures.models.FuturesPrice;

import java.util.Date;
import java.util.Properties;

public class MarketMakerStrategyTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("exchange", "test");
        properties.put("instrument-type", "future");
        properties.put("trade-instrument-id", "instr1");
        properties.put("hedge-instrument-id", "instr1");

        MarketMakerStrategy strategy = new MarketMakerStrategy(properties);
        FuturesPrice px1 = new FuturesPrice();
        px1.setInstrumentId("instr1");
        px1.setBestBid(100.0);
        px1.setBestAsk(102.0);
        px1.setLast(101.0);
        px1.setTimestamp(new Date());
        px1.setLastQty(1);

        //this will fail since no dummy trade service is present
        strategy.onPriceUpdate(px1);
    }
}
