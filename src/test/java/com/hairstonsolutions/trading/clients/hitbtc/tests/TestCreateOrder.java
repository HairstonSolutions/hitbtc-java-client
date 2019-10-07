package com.hairstonsolutions.trading.clients.hitbtc.tests;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.orders.CreateOrder;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import org.junit.Test;

public class TestCreateOrder {

    @Test
    public void testGetAllDefault() {
        CreateOrder myCreateOrder = new CreateOrder();

        assert("BTCUSD".equals(myCreateOrder.getSymbol()));
        assert(myCreateOrder.getSide().equals(Side.BUY));
        assert(myCreateOrder.getTradeType().equals(TradeType.LIMIT));
        assert(myCreateOrder.getTimeInForce().equals(TimeInForce.GTC_GOOD_TILL_CANCELLED));
    }

    @Test
    public void testGeters() {
        String mySymbol = "LTCBTC";
        String buyside = "sell";
        String tradeType = "market";
        String timeforce = "FOK";
        CreateOrder myCreateOrder = new CreateOrder(mySymbol,buyside,tradeType,timeforce);

        assert(myCreateOrder.getSymbol().equals("LTCBTC"));
        assert(myCreateOrder.getSide().equals("sell"));
        assert(myCreateOrder.getTradeType().equals("market"));
        assert(myCreateOrder.getTimeInForce().equals("FOK"));
    }

}
