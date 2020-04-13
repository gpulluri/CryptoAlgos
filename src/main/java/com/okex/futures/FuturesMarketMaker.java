package com.okex.futures;

import com.okex.futures.strategies.MarketMakerStrategy;

import java.util.Properties;

public class FuturesMarketMaker {
    public static void main(String[] args) {

        String wsUrl = "wss://real.okex.com:8443/ws/v3";
        String restUrl = "https://testnet.okex.com";
        String apiKey = "e3ed104a-d291-497e-a040-7ca6e5afdee3";
        String secretKey = "F18424B8C359A017D841673FEE48CDE9";
        String passphrase = "oktest";

        Properties properties = new Properties();
        properties.put("exchange", "okex");
        properties.put("instrument-type", "futures");
        properties.put("trade-instrument-id", "TBTC-USD-200925");
        properties.put("hedge-instrument-id", "TBTC-USD-200626");
        properties.put("websocket-url", wsUrl);
        properties.put("rest-url", restUrl);
        properties.put("api-key", apiKey);
        properties.put("secret-key", secretKey);
        properties.put("pass-phrase", passphrase);

        MarketMakerStrategy strategy = new MarketMakerStrategy(properties);
        strategy.run();
    }
}
