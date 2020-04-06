package com.hairstonsolutions.trading.clients.hitbtc.tests.trades;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI, orderId);

        for (Trade trades : tradeReport) {
            System.out.println(trades);
        }
        System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

        String averagePrice = Trade.getAveragePrice(tradeReport);
        System.out.printf("Average Price: %s\n", averagePrice);
        assert averagePrice.equals("8835.45");
    }

    @Test
    public void calculateTotalFee() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI, orderId);

        for (Trade trades : tradeReport) {
            System.out.println(trades);
        }
        System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

        String totalFees = Trade.getTotalFee(tradeReport);
        System.out.printf("Total Fees: %s\n", totalFees);
        assert totalFees.equals("0.04205673360000001");
    }
}
