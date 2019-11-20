package com.hairstonsolutions.trading.clients.hitbtc.account;

import lombok.Data;

@Data
public class Balance {
    private String currency;
    private String available;
    private String reserved;

    public Balance() {
    }

    public Balance(String currency, String available, String reserved) {
        this.currency = currency;
        this.available = available;
        this.reserved = reserved;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAvailable() {
        return available;
    }

    public String getReserved() {
        return reserved;
    }
}
