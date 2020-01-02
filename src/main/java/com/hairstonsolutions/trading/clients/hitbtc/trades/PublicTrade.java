package com.hairstonsolutions.trading.clients.hitbtc.trades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicTrade {

    private long id;
    private String price;
    private String quantity;
    private String side;
    private String timestamp;

    public PublicTrade() {
    }

    public String toJSON() {
        return "{ " +
                "\"id\": \"" + id + "\", " +
                "\"price\": \"" + price + "\", " +
                "\"quantity\": \"" + quantity + "\", " +
                "\"side\": \"" + side + "\", " +
                "\"timestamp\": \"" + timestamp + "\"" +
                " }";
    }
}
