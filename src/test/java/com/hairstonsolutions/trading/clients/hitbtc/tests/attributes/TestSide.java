package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import org.junit.Test;

public class TestSide {

    @Test
    public void testBuy() {
        String side = Side.selectSide("buy");
        assert (side.equals("buy"));
    }

    @Test
    public void testSell() {
        String side = Side.selectSide("sell");
        assert (side.equals("sell"));
    }

    @Test
    public void testEmptyInput() {
        String side = Side.selectSide("");
        assert (side.equals(Side.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        String side = Side.selectSide("dsafasf");
        assert (side.equals(Side.DEFAULT));
    }

}
