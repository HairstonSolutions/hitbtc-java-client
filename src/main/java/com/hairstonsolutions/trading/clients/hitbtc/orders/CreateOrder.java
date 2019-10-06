package com.hairstonsolutions.trading.clients.hitbtc;

public class CreateOrder {
    public static final String BUY = "buy";
    public static final String SELL = "sell";

    public static final String LIMIT = "limit";
    public static final String MARKET = "market";
    public static final String STOPLIMIT = "stopLimit";
    public static final String STOPMARKET = "stopMarket";

    public static final String TIMEINFORCE_GTC = "GTC";
    public static final String TIMEINFORCE_GTC = "GTC";
    public static final String TIMEINFORCE_GTC = "GTC";
    public static final String TIMEINFORCE_GTC = "GTC";
    public static final String TIMEINFORCE_GTC = "GTC";


    private String symbol;
    private String side;
    private String type;
    private String timeInForce;



    public CreateOrder() {
        this.symbol = "BTCUSD";
        this.side = BUY;
        this.type = LIMIT;
    }

    public CreateOrder(String symbol, String side, String type) {
        this.symbol = symbol;
        this.side = side;
        this.type = type;
    }

    public CreateOrder(String symbol, String side) {
        this.symbol = symbol;
        this.side = side;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side;
    }

    public String getType() {
        return type;
    }

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
}
