package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class Side {
    public static final String BUY = "buy";
    public static final String SELL = "sell";

    private String side;

    public Side() {
        this.side = BUY;
    }

    public Side(String side) {
        setSide(side);
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        switch (side) {
            case "buy":
                this.side = BUY;
                break;
            case "sell":
                this.side = SELL;
                break;
            default:
                this.side = BUY;
        }
    }

    @Override
    public String toString() {
        return side;
    }
}
