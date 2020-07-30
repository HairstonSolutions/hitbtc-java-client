package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TradeType {
    public static final String LIMIT = "limit";
    public static final String MARKET = "market";
    public static final String STOP_LIMIT = "stopLimit";
    public static final String STOP_MARKET = "stopMarket";
    public static final String DEFAULT = LIMIT;

    public static String selectTradeType(String tradeType) {
        String tradeTypeSetting;

        switch (tradeType) {
            case "limit":
                tradeTypeSetting = LIMIT;
                break;
            case "market":
                tradeTypeSetting = MARKET;
                break;
            case "stopLimit":
                tradeTypeSetting = STOP_LIMIT;
                break;
            case "stopMarket":
                tradeTypeSetting = STOP_MARKET;
                break;
            default:
                tradeTypeSetting = DEFAULT;
        }
        return tradeTypeSetting;
    }
}
