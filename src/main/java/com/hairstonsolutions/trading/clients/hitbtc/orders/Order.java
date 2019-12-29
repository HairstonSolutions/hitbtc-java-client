package com.hairstonsolutions.trading.clients.hitbtc.orders;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Status;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import lombok.Data;

@Data
public class Order {
    private long id;
    private String clientOrderId;
    private String symbol;
    private Side side;
    private Status status;
    private TradeType type;
    private TimeInForce timeInForce;
    private String price;
    private String quantity;
    private boolean postOnly;
    private String cumQuantity;
    private String createdAt;   //@TODO Convert to Object TimeStamp
    private String updatedAt;   //@TODO Convert to Object TimeStamp
    private String stopPrice;
    private String expireTime;
    private Trade[] tradesReport;

    public long getId() {
        return id;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public Side getSide() {
        return side;
    }

    public Status getStatus() {
        return status;
    }

    public TradeType getType() {
        return type;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public String getCumQuantity() {
        return cumQuantity;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public Trade[] getTradesReport() {
        return tradesReport;
    }

    public void setTradesReport(Trade[] tradesReport) {
        this.tradesReport = tradesReport;
    }

    public void pullTradesReport(HitBtcAPI hitBtcAPI) {
        if (this.getStatus().toString().equals(Status.FILLED))
            this.tradesReport = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI, getId());
    }
}



/*

LIST ALL ACTIVE ORDERS: /order
  {
    "id": 828680665,
    "clientOrderId": "f4307c6e507e49019907c917b6d7a084",
    "symbol": "ETHBTC",
    "side": "sell",
    "status": "partiallyFilled",
    "type": "limit",
    "timeInForce": "GTC",
    "price": "0.011384",
    "quantity": "13.942",
    "postOnly": false,
    "cumQuantity": "5.240",
    "createdAt": "2017-01-16T14:18:47.321Z",
    "updatedAt": "2017-01-19T15:23:54.876Z"
  }

GET SINGLE ORDER BY CLIENT ID: /order/{clientOrderId}
{
  "id": 828680665,
  "clientOrderId": "f4307c6e507e49019907c917b6d7a084",
  "symbol": "ETHBTC",
  "side": "sell",
  "status": "partiallyFilled",
  "type": "limit",
  "timeInForce": "GTC",
  "price": "0.011384",
  "quantity": "13.942",
  "postOnly": false,
  "cumQuantity": "5.240",
  "createdAt": "2017-01-16T14:18:47.321Z",
  "updatedAt": "2017-01-19T15:23:54.876Z"
}

HISTORY ORDER: /history/order
  {
    "id": 828680665,
    "clientOrderId": "f4307c6e507e49019907c917b6d7a084",
    "symbol": "ETHBTC",
    "side": "sell",
    "status": "partiallyFilled",
    "type": "limit",
    "timeInForce": "GTC",
    "price": "0.011384",
    "quantity": "13.942",
    "postOnly": false,
    "cumQuantity": "5.240",
    "createdAt": "2017-01-16T14:18:47.321Z",
    "updatedAt": "2017-01-19T15:23:54.876Z"
  }
 */
