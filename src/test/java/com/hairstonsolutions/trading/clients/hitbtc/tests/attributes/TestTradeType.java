package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import org.junit.Test;

public class TestTradeType {

    @Test
    public void testDefault() {
        TradeType tradeType = new TradeType();

        assert (tradeType.getType().equals("limit"));
        assert (tradeType.getType().equals(TradeType.LIMIT));
    }

    @Test
    public void testLimit() {
        TradeType tradeType = new TradeType("limt");

        assert (tradeType.getType().equals("limit"));
        assert (tradeType.getType().equals(TradeType.LIMIT));
    }

    @Test
    public void testMarket() {
        TradeType tradeType = new TradeType("market");

        assert (tradeType.getType().equals("market"));
        assert (tradeType.getType().equals(TradeType.MARKET));
    }

    @Test
    public void testStopLimit() {
        TradeType tradeType = new TradeType("stopLimit");

        assert (tradeType.getType().equals("stopLimit"));
        assert (tradeType.getType().equals(TradeType.STOPLIMIT));
    }

    @Test
    public void testStopMarket() {
        TradeType tradeType = new TradeType("stopMarket");

        assert (tradeType.getType().equals("stopMarket"));
        assert (tradeType.getType().equals(TradeType.STOPMARKET));
    }

    @Test
    public void testEmptyInput() {
        TradeType tradeType = new TradeType("");

        assert (tradeType.getType().equals("limit"));
        assert (tradeType.getType().equals(TradeType.LIMIT));
    }

    @Test
    public void testWrongInput() {
        TradeType tradeType = new TradeType("dafsa");

        assert (tradeType.getType().equals("limit"));
        assert (tradeType.getType().equals(TradeType.LIMIT));
    }

    @Test
    public void testObjectPrintout() {
        TradeType tradeType = new TradeType();

        assert (tradeType.toString().equals(TradeType.LIMIT));
    }
}
