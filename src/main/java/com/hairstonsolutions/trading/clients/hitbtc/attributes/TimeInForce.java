package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class TimeInForce {
    public static final String GTC_GOOD_TILL_CANCELLED = "GTC";
    public static final String IOC_IMMEDIATE_OR_CANCEL = "IOC";
    public static final String FOK_FILL_OR_KILL = "FOK";
    public static final String DAY = "Day";
    public static final String GTD_GOOD_TILL_DATETIME = "GTD";

    private String force;

    public TimeInForce() {
        this.force = GTC_GOOD_TILL_CANCELLED;
    }

    public TimeInForce(String force) {
        this.setForce(force);
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        if (force.equals("GTC"))
            this.force = GTC_GOOD_TILL_CANCELLED;
        else if (force.equals("IOC"))
            this.force = IOC_IMMEDIATE_OR_CANCEL;
        else if (force.equals("FOK"))
            this.force = FOK_FILL_OR_KILL;
        else if (force.equals("Day"))
            this.force = DAY;
        else if (force.equals("GTD"))
            this.force = GTD_GOOD_TILL_DATETIME;
        else
            this.force = GTC_GOOD_TILL_CANCELLED;
    }

    @Override
    public String toString() {
        return force;
    }
}
