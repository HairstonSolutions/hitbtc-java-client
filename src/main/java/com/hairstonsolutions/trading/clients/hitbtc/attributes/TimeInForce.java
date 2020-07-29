package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TimeInForce {
    public static final String GTC_GOOD_TILL_CANCELLED = "GTC";
    public static final String IOC_IMMEDIATE_OR_CANCEL = "IOC";
    public static final String FOK_FILL_OR_KILL = "FOK";
    public static final String DAY = "Day";
    public static final String GTD_GOOD_TILL_DATETIME = "GTD";
    public static final String DEFAULT = GTC_GOOD_TILL_CANCELLED;

    public static String selectTimeInForce(String timeInForce) {

        String timeInForceSetting;

        switch (timeInForce) {
            case "GTC":
                timeInForceSetting = GTC_GOOD_TILL_CANCELLED;
                break;
            case "IOC":
                timeInForceSetting = IOC_IMMEDIATE_OR_CANCEL;
                break;
            case "FOK":
                timeInForceSetting = FOK_FILL_OR_KILL;
                break;
            case "Day":
                timeInForceSetting = DAY;
                break;
            case "GTD":
                timeInForceSetting = GTD_GOOD_TILL_DATETIME;
                break;
            default:
                timeInForceSetting = DEFAULT;
        }
        return timeInForceSetting;
    }
}
