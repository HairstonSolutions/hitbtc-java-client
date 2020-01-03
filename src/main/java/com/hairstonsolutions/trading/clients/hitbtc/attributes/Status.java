package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class Status {
    public static final String NEW = "new";
    public static final String SUSPENDED = "suspended";
    public static final String PARTIALLY_FILLED = "partiallyFilled";
    public static final String FILLED = "filled";
    public static final String CANCELED = "canceled";
    public static final String EXPIRED = "expired";
    public static final String DEFAULT = NEW;

    public static String selectStatus(String status) {

        String statusSetting;

        switch (status) {
            case "new":
                statusSetting = NEW;
                break;
            case "suspended":
                statusSetting = SUSPENDED;
                break;
            case "partiallyFilled":
                statusSetting = PARTIALLY_FILLED;
                break;
            case "filled":
                statusSetting = FILLED;
                break;
            case "canceled":
                statusSetting = CANCELED;
                break;
            case "expired":
                statusSetting = EXPIRED;
                break;
            default:
                statusSetting = DEFAULT;
        }
        return statusSetting;
    }
}