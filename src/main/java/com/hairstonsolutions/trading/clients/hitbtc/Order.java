package com.hairstonsolutions.trading.clients.hitbtc;

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

public class Order {
}
