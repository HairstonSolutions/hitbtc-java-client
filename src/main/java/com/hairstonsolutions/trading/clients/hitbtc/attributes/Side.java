package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class Side {
    public static final String BUY = "buy";
    public static final String SELL = "sell";
    public static final String DEFAULT = BUY;

    public static String selectSide(String side) {
        String sideSetting;

        switch (side) {
            case "buy":
                sideSetting = BUY;
                break;
            case "sell":
                sideSetting = SELL;
                break;
            default:
                sideSetting = DEFAULT;
        }
        return sideSetting;
    }
}
