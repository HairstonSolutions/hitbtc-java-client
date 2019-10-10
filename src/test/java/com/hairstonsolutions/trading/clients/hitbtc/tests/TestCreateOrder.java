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
        assert(myCreateOrder.getPrice().equals("10000.00"));
        assert(myCreateOrder.getQuantity().equals("0.0001"));
    }

    @Test
    public void testGeters() {
        String mySymbol = "LTCBTC";
        String buyside = "sell";
        String tradeType = "market";
        String timeforce = "FOK";
        String price = "8500.53";
        String quantity = "1.1234";
        CreateOrder myCreateOrder = new CreateOrder(mySymbol,buyside,tradeType,timeforce,price,quantity);

        assert(myCreateOrder.getSymbol().equals("LTCBTC"));
        assert(myCreateOrder.getSide().equals("sell"));
        assert(myCreateOrder.getTradeType().equals("market"));
        assert(myCreateOrder.getTimeInForce().equals("FOK"));
        assert(myCreateOrder.getPrice().equals("8500.53"));
        assert(myCreateOrder.getQuantity().equals("1.1234"));
    }

}
