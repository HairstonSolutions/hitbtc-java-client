package com.hairstonsolutions.trading.clients.hitbtc.orders;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Status;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Order {
    private long id;
    private String clientOrderId;
    private String symbol;
    private String side;
    private String status;
    private String type;
    private String timeInForce;
    private String price;
    private String quantity;
    private boolean postOnly;
    private String cumQuantity;
    private String createdAt;
    private String updatedAt;
    private String avgPrice;
    private String stopPrice;
    private String expireTime;
    private List<Trade> tradesReport = new LinkedList<>();

    public long getId() {
        return id;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTimeInForce() {
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

    public List<Trade> getTradesReport() {
        return tradesReport;
    }

    public void setSide(String side) {
        this.side = Side.selectSide(side);
    }

    public void setStatus(String status) {
        this.status = Status.selectStatus(status);
    }

    public void setType(String tradeType) {
        this.type = TradeType.selectTradeType(tradeType);
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = TimeInForce.selectTimeInForce(timeInForce);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTradesReport(List<Trade> tradesReport) {
        this.tradesReport = tradesReport;
    }

    public void pullTradesReport(HitBtcAPI hitBtcAPI) {
        if (this.getStatus().equals(Status.FILLED)) {
            this.tradesReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, this.id);
            calculatePriceAsTradeReportPriceAverage();
        }
    }

    /*
      The HitBTC API response of Market (FOK / IOC) Trades naturally has incomplete data.
      The good thing is that the missing info can be derived from the data that is returned.
      This method Calculates those values & fills them in for both the Order and Trade Objects.
    */
    public void reconcileMarketOrder() {
        if (status.equals(Status.FILLED) && !tradesReport.isEmpty()) {
            reconcileRetrievedTradeReport();
            calculatePriceAsTradeReportPriceAverage();
        }
    }

    public void reconcileRetrievedTradeReport() {
        for (Trade trade : tradesReport) {
            trade.setClientOrderId(clientOrderId);
            trade.setOrderId(id);
            trade.setSymbol(symbol);
            trade.setSide(side);
        }
    }

    private void calculatePriceAsTradeReportPriceAverage() {
        setAvgPrice(Trade.getPreciseAveragePrice(tradesReport));
        setPrice(getAvgPrice());
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
