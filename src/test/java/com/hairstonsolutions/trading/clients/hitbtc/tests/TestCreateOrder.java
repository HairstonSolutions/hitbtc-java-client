package com.hairstonsolutions.trading.clients.hitbtc.tests;

import com.hairstonsolutions.trading.clients.hitbtc.orders.CreateOrder;

import com.hairstonsolutions.trading.clients.hitbtc.trades.TradeType;
import org.junit.Test;

public class TestCreateOrder {

    @Test
    public void testGetAllDefault() {
        CreateOrder myCreateOrder = new CreateOrder();
        System.out.println(myCreateOrder.getSymbol());
        assert("BTCUSD".equals(myCreateOrder.getSymbol()));
        assert(myCreateOrder.getSide().equals(CreateOrder.BUY));
        assert(myCreateOrder.getTradeType().equals(TradeType.LIMIT));
    }

    @Test
    public void testGeters() {
        String mySymbol = "LTCBTC";
        String buyside = "buy";
        String tradeType = "limit";
        String timeforce = "GTC";
        CreateOrder myCreateOrder = new CreateOrder(mySymbol,buyside,tradeType);

        assert(myCreateOrder.getSymbol().equals("LTCBTC"));
        assert(myCreateOrder.getSide().equals("buy"));
        assert(myCreateOrder.getTradeType().equals("limit"));

    }

}
