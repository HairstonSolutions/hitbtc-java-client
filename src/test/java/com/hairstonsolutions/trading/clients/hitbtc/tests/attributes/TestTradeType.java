package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import org.junit.Test;

public class TestTradeType {

    @Test
    public void testDefault() {
        String tradeType = TradeType.selectTradeType(TradeType.DEFAULT);
        assert (tradeType.equals(TradeType.LIMIT));
    }

    @Test
    public void testLimit() {
        String tradeType = TradeType.selectTradeType("limit");
        assert (tradeType.equals(TradeType.LIMIT));
    }

    @Test
    public void testMarket() {
        String tradeType = TradeType.selectTradeType("market");
        assert (tradeType.equals(TradeType.MARKET));
    }

    @Test
    public void testStopLimit() {
        String tradeType = TradeType.selectTradeType("stopLimit");
        assert (tradeType.equals(TradeType.STOP_LIMIT));
    }

    @Test
    public void testStopMarket() {
        String tradeType = TradeType.selectTradeType("stopMarket");
        assert (tradeType.equals(TradeType.STOP_MARKET));
    }

    @Test
    public void testEmptyInput() {
        String tradeType = TradeType.selectTradeType("");
        assert (tradeType.equals(TradeType.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        String tradeType = TradeType.selectTradeType("dafsa");
        assert (tradeType.equals(TradeType.DEFAULT));
    }
}
