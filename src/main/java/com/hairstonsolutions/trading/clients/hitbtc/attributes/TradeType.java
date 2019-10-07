package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TradeType {
    public static final String LIMIT = "limit";
    public static final String MARKET = "market";
    public static final String STOPLIMIT = "stopLimit";
    public static final String STOPMARKET = "stopMarket";

    private String type;

    public TradeType() {
        setType(LIMIT);
    }

    public TradeType(String type) {
        setType(type);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if (type.equals("limit"))
            this.type = LIMIT;
        else if (type.equals("market"))
            this.type = MARKET;
        else if (type.equals("stopLimit"))
            this.type = STOPLIMIT;
        else if (type.equals("stopMarket"))
            this.type = STOPMARKET;
        else
            this.type = LIMIT;
    }

    @Override
    public String toString() {
        return type;
    }
}
