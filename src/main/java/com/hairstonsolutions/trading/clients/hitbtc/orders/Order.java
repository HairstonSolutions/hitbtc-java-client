package com.hairstonsolutions.trading.clients.hitbtc.orders;

import com.hairstonsolutions.trading.clients.hitbtc.Symbol;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.Data;

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

@Data
public class Order {
    long id;
    String clientOrderId;
    String symbol;
    Side side;
    String status;
    String type;        //@TODO Convert to Object TradeType
    String timeInForce; //@TODO Convert to Object TimeInForce
    String price;
    String quantity;
    String postOnly;
    String cumQuantity;
    String createdAt;   //@TODO Convert to Object TimeStamp
    String updatedAt;   //@TODO Convert to Object TimeStamp
}
