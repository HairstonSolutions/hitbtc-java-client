package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TradeType {
    public static final String LIMIT = "limit";
    public static final String MARKET = "market";
    public static final String STOPLIMIT = "stopLimit";
    public static final String STOPMARKET = "stopMarket";
    public static final String DEFAULT = LIMIT;

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
        switch(type) {
            case "limit":
                this.type = LIMIT;
                break;
            case "market":
                this.type = MARKET;
                break;
            case "stopLimit":
                this.type = STOPLIMIT;
                break;
            case "stopMarket":
                this.type = STOPMARKET;
                break;
            default:
                this.type = DEFAULT;
        }
    }

    @Override
    public String toString() {
        return type;
    }
}
