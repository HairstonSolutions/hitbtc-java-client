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
        if (side.equals("buy"))
            this.side = BUY;
        else if (side.equals("sell"))
            this.side = SELL;
        else
            this.side = BUY;
    }

    @Override
    public String toString() {
        return side;
    }
}
