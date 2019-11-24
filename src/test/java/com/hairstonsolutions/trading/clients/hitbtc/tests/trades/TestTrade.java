package com.hairstonsolutions.trading.clients.hitbtc.tests.trades;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;

public class TestTrade {

    private final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void calculateAveragePrice() {
        long orderId =176617860956L;
        Trade[] tradeReport = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI,orderId);

        for ( Trade trades : tradeReport ) {
            System.out.println(trades);
        }
        System.out.println(String.format("Order Trades Total: %s", tradeReport.length));

        String averagePrice = Trade.getAveragePrice(tradeReport);
        System.out.printf("Average Price: %s\n", averagePrice);
        assert averagePrice.equals("8835.45");
    }
}
