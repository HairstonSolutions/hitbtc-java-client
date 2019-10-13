package com.hairstonsolutions.trading.clients.hitbtc.tests.trades;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.junit.Test;

public class TestPublicTrade {
    @Test
    public void testDefault() {
        PublicTrade publicTrade = new PublicTrade();
        assert(publicTrade.getId() == 12345678);
        assert(publicTrade.getPrice().equals("8368.51"));
        assert(publicTrade.getQuantity().equals("0.05162"));
        assert(publicTrade.getSide().equals(Side.BUY));
        assert(publicTrade.getTimestamp().equals("2019-10-11T09:12:02.579Z"));
    }

    @Test
    public void testCreateValues() {
        int id = 87654321;
        String price = "100.00";
        String quantity = "12.0";
        String side = "sell";
        String timestamp = "2019-12-11T12:12:02.579Z";
        PublicTrade publicTrade = new PublicTrade(id,price,quantity,side,timestamp);

        assert(publicTrade.getId() == 87654321);
        assert(publicTrade.getPrice().equals("100.00"));
        assert(publicTrade.getQuantity().equals("12.0"));
        assert(publicTrade.getSide().equals(Side.SELL));
        assert(publicTrade.getTimestamp().equals("2019-12-11T12:12:02.579Z"));
    }

    @Test
    public void testSetters() {
        int id = 87654321;
        String price = "100.00";
        String quantity = "12.0";
        String side = "buy";
        String timestamp = "2019-12-11T12:12:02.579Z";
        PublicTrade publicTrade = new PublicTrade(id,price,quantity,side,timestamp);

        publicTrade.setId(6547839);
        publicTrade.setPrice("3.99");
        publicTrade.setQuantity("2.0");
        publicTrade.setSide("sell");
        publicTrade.setTimestamp("2019-10-08T03:04:11.123Z");

        assert(publicTrade.getId() != 12345678);
        assert(publicTrade.getPrice().equals("3.99"));
        assert(publicTrade.getQuantity().equals("2.0"));
        assert(publicTrade.getSide().equals(Side.SELL));
        assert(publicTrade.getTimestamp().equals("2019-10-08T03:04:11.123Z"));
    }

    @Test
    public void testObjectPrintout() {
        PublicTrade publicTrade = new PublicTrade();
        assert(publicTrade.toString().equals("{ \"id\": \"12345678\", \"price\": \"8368.51\", \"quantity\": \"0.05162\", \"side\": \"buy\", \"timestamp\": \"2019-10-11T09:12:02.579Z\" }"));
        System.out.println(publicTrade.toJSON());
        System.out.println(publicTrade.toString());
    }

}
