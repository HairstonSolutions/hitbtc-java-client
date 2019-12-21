package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TimeInForce {
    public static final String GTC_GOOD_TILL_CANCELLED = "GTC";
    public static final String IOC_IMMEDIATE_OR_CANCEL = "IOC";
    public static final String FOK_FILL_OR_KILL = "FOK";
    public static final String DAY = "Day";
    public static final String GTD_GOOD_TILL_DATETIME = "GTD";
    public static final String DEFAULT = GTC_GOOD_TILL_CANCELLED;

    private String timeInForce;

    public TimeInForce() {
        this.timeInForce = GTC_GOOD_TILL_CANCELLED;
    }

    public TimeInForce(String timeInForce) {
        this.setTimeInForce(timeInForce);
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        switch(timeInForce) {
            case "GTC":
                this.timeInForce = GTC_GOOD_TILL_CANCELLED;
                break;
            case "IOC":
                this.timeInForce = IOC_IMMEDIATE_OR_CANCEL;
                break;
            case "FOK":
                this.timeInForce = FOK_FILL_OR_KILL;
                break;
            case "Day":
                this.timeInForce = DAY;
                break;
            case "GTD":
                this.timeInForce = GTD_GOOD_TILL_DATETIME;
                break;
            default:
                this.timeInForce = DEFAULT;
        }
    }

    @Override
    public String toString() {
        return timeInForce;
    }
}
