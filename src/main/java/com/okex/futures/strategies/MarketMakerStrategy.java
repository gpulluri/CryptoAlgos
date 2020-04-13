package com.okex.futures.strategies;


import com.okex.futures.models.FuturesPrice;
import com.okex.futures.models.OrderAck;
import com.okex.futures.models.OrderUpdate;

import java.util.Properties;

public class MarketMakerStrategy extends BaseStrategy {

    private String instrumentType = "futures";
    private String tradeInstrumentId = "TBTC-USD-200925";
    private String hedgeInstrumentId = "TBTC-USD-200626";

    String buyOrderId = null;
    String sellOrderId = null;
    String hedgeBuyOrderId = null;
    String hedgeSellOrderId = null;
    boolean hedgeBuy = false;
    boolean hedgeSell = false;

    Double bidPx = 0.0;
    Double askPx = 0.0;

    public MarketMakerStrategy(Properties properties) {
        super(properties);
        try {
            instrumentType = properties.get("instrument-type").toString();
            tradeInstrumentId = properties.get("trade-instrument-id").toString();
            hedgeInstrumentId = properties.get("hedge-instrument-id").toString();
        } catch(Exception ex) {
            System.out.println("Required properties missing");
            this.destroy();
        }
    }

    @Override
    public void onOrderUpdate(OrderUpdate update) {
        System.out.println("Order update: "+update);
        //String instrumentId = update.getInstrumentId();
        String orderType = update.getType();
        String orderId = update.getOrderId();

        if(orderType.equals("1")) {
            if(orderId.equals(this.buyOrderId)) {
                //if order is filled - get ready to place hedge sell order
                if(update.getFilledQty() == 1)  {
                    hedgeSell = true;
                } else if(!update.getErrorCode().equals("0")) {
                    //if order failed with error code - place buy order again?
                    System.out.println("Error in buy order: "+update);
                }
            } else if(orderId.equals(this.hedgeBuyOrderId)){
                //if hedge order is filled - get ready to place sell order
                if(update.getFilledQty() == 1) {
                    sellOrderId = null;
                } else if(!update.getErrorCode().equals("0")) {
                    //if hedge order failed with error code - place hedge buy order again?
                    System.out.println("Error in hedge order: "+update);
                }
            }
        }
        else if(orderType.equals("2")) {
            if(orderId.equals(this.sellOrderId)) {
                //if order is filled - get ready to place sell order
                if(update.getFilledQty() == 1) hedgeBuy = true;
                //if order failed with error code - place buy order again?
                else if(!update.getErrorCode().equals("0")) {
                    System.out.println("Error in sell order: "+update);
                }

            } else if(orderId.equals(this.hedgeSellOrderId)){
                //if hedge order is filled - get ready to place buy order
                if(update.getFilledQty() == 1) {
                    buyOrderId = null;
                }
                //if hedge order failed with error code - place hedge sell order again?
                else if(!update.getErrorCode().equals("0")) {
                    System.out.println("Error in hedge order: "+update);
                }
            }
        }
    }

    @Override
    public void onPriceUpdate(FuturesPrice px) {
        System.out.println("Price update: "+px);
        //1:open long 2:open short 3:close long 4:close short

        if(px.getInstrumentId().equals(tradeInstrumentId)) {
            if(buyOrderId == null) {
                OrderAck ack = sendOrder("1",tradeInstrumentId, px.getBestBid()-1, 1, 0);
                buyOrderId = ack.getOrderId();
                bidPx = px.getBestBid() - 1;
            } else {
                //cancel/replace order if price difference crosses threshold
                if(Math.abs(px.getBestBid() - bidPx) < 0.8) {
                    System.out.println("cancel/replacing buy trade order");
                    cancelOrder(tradeInstrumentId, buyOrderId);
                    buyOrderId = null;
                }
            }

            if(sellOrderId == null) {
                OrderAck ack = sendOrder("2",tradeInstrumentId, px.getBestAsk()+1, 1, 0);
                sellOrderId = ack.getOrderId();
                askPx = px.getBestAsk() + 1;
            } else {
                if(Math.abs(px.getBestAsk() - askPx) < 0.8) {
                    System.out.println("cancel/replacing sell trade order");
                    cancelOrder(tradeInstrumentId, sellOrderId);
                    sellOrderId = null;
                }
            }
        } else if(px.getInstrumentId().equals(hedgeInstrumentId)) {
            if(hedgeBuy) {
                //BBO order - price will be ignored
                System.out.println("Sending hedge buy order");
                OrderAck ack = sendOrder("1",hedgeInstrumentId, px.getBestBid(), 1, 1);
                hedgeBuyOrderId = ack.getOrderId();
                hedgeBuy = false;
            }
            if(hedgeSell) {
                System.out.println("Sending hedge sell order");
                OrderAck ack = sendOrder("2",hedgeInstrumentId, px.getBestAsk(), 1, 1);
                hedgeSellOrderId = ack.getOrderId();
                hedgeSell = false;
            }

        } else {
            System.out.println("Unknow price update...");
        }
    }

    @Override
    public void run() {
        subscribe(instrumentType+"/ticker:"+tradeInstrumentId, instrumentType+"/ticker:"+hedgeInstrumentId);
        subscribe(instrumentType+"/order:"+tradeInstrumentId, instrumentType+"/order:"+hedgeInstrumentId);
    }
}
