package com.hairstonsolutions.trading.clients.hitbtc.orders;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;

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
    private String symbol;
    private Side side;
    private TradeType tradeType;
    private TimeInForce timeInForce;


    public CreateOrder() {
        this.symbol = "BTCUSD";
        this.side = new Side(Side.BUY);
        this.tradeType = new TradeType(TradeType.LIMIT);
        this.timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);
    }

    public CreateOrder(String symbol, String side, String tradeType, String timeInForce) {
        this.symbol = symbol;
        this.side = new Side(side);
        this.tradeType = new TradeType(tradeType);
        this.timeInForce = new TimeInForce(timeInForce);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side.getSide();
    }

    public String getTradeType() {
        return tradeType.getType();
    }

    public String getTimeInForce() {
        return timeInForce.getForce();
    }
}
