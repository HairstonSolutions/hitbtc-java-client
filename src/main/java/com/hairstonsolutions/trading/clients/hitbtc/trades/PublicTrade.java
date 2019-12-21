package com.hairstonsolutions.trading.clients.hitbtc.trades;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicTrade {

    private long id;
    private String price;
    private String quantity;
    private Side side;
    private String timestamp;

    public PublicTrade() {
        this.side = new Side();
    }

    public String getSide() {
        return side.getSide();
    }

    public void setSide(String side) {
        this.side.setSide(side);
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
