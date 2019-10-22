package com.hairstonsolutions.trading.clients.hitbtc.trades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicTrade {
    private int id;
    private String price;
    private String quantity;
    private Side side;
    private String timestamp;

    public PublicTrade() {
        this.id = 12345678;
        this.price = "8368.51";
        this.quantity = "0.05162";
        this.side = new Side();
        this.timestamp = "2019-10-11T09:12:02.579Z";
    }

    public PublicTrade(int id, String price, String quantity, String side, String timestamp) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.side = new Side(side);
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSide() {
        return side.getSide();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSide(String side) {
        this.side.setSide(side);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return toJSON();
    }
}