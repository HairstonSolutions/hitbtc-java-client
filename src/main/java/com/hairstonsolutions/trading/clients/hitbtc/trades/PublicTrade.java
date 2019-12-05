package com.hairstonsolutions.trading.clients.hitbtc.trades;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class PublicTrade {
    @Id
    private long aggregateId;

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

/*
MYSQL TABLE CREATION SCHEMA:
create table public_trade
(
    aggregate_id    double            auto_increment  primary key,
    id              double             null            UNIQUE,
    price           varchar(255)    null,
    quantity        varchar(255)    null,
    timestamp       varchar(255)    null
);

HAS A DEPENDANCY ON THE SIDE TABLE WITHIN THE SIDE CLASS
 */