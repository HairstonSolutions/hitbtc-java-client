package com.hairstonsolutions.trading.clients.hitbtc.orders;

import com.hairstonsolutions.trading.clients.hitbtc.trades.TradeType;

/*
    {
  "symbol": "BTCUSD",
  "side": "buy",
  "type": "limit",
  "timeInForce": "GTC",
  "price": "10055.16",
  "quantity": "0.0001",
  "postOnly": false
}
*/

public class CreateOrder {
    public static final String BUY = "buy";
    public static final String SELL = "sell";

    private String symbol;
    private String side;
    private TradeType tradeType;
    private TimeInForce timeInForce;


    public CreateOrder() {
        this.symbol = "BTCUSD";
        this.side = BUY;
        this.tradeType = new TradeType(TradeType.LIMIT);
        this.timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);
    }

    public CreateOrder(String symbol, String side, String tradeType, String timeInForce) {
        this.symbol = symbol;
        this.side = side;
        this.tradeType = new TradeType(tradeType);
        this.timeInForce = new TimeInForce(timeInForce);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side;
    }

    public String getTradeType() {
        return tradeType.getType();
    }

    public String getTimeInForce() {
        return timeInForce.getForce();
    }
}
