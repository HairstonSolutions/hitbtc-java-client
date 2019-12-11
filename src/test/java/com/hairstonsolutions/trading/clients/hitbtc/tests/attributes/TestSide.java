package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import org.junit.Test;

public class TestSide {
    @Test
    public void testDefault() {
        Side side = new Side();
        assert (side.getSide().equals("buy"));
        assert (side.getSide().equals(Side.DEFAULT));
    }

    @Test
    public void testBuy() {
        Side side = new Side("buy");
        assert (side.getSide().equals("buy"));
    }

    @Test
    public void testSell() {
        Side side = new Side("sell");
        assert (side.getSide().equals("sell"));
    }

    @Test
    public void testEmptyInput() {
        Side side = new Side("");
        assert (side.getSide().equals(Side.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        Side side = new Side("dsafasf");
        assert (side.getSide().equals(Side.DEFAULT));
    }

    @Test
    public void testObjectPrintout() {
        Side side = new Side();
        assert (side.getSide().equals(Side.DEFAULT));
    }
}
