package com.hairstonsolutions.trading.clients.hitbtc.trades;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.Data;

@Data
public class Trade {
    long id;
    String clientOrderId;
    long orderId;
    String symbol;
    Side side;
    String quantity;
    String price;
    String fee;
    String timestamp;
}

/*
HISTORY TRADES: /history/trades
  {
    "id": 0,
    "clientOrderId": "string",
    "orderId": 0,
    "symbol": "string",
    "side": "sell",
    "quantity": "string",
    "fee": "string",
    "price": "string",
    "timestamp": "2019-10-05T20:18:54.426Z"
  }

  HISTORY ORDER TRADES: /history/order/{id}/trades
    {
    "id": 0,
    "clientOrderId": "string",
    "orderId": 0,
    "symbol": "string",
    "side": "sell",
    "quantity": "string",
    "fee": "string",
    "price": "string",
    "timestamp": "2019-10-05T20:17:27.687Z"
  }
 */