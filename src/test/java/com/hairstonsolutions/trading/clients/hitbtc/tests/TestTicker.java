package com.hairstonsolutions.trading.clients.hitbtc.tests;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import org.junit.Test;

public class TestTicker {

    @Test
    public void marketProfit() {
        Ticker btc = new Ticker("BTCUSD", "8417.70", "8417.16", "8416.53", "8368.11",
                "8626.14", "8553.32", "22458.00450", "190435912.0240078",
                "2019-11-18T12:42:48.640Z");
        String quantity = "0.01";
        String profit = btc.getPotentialProfit(quantity);

        assert profit.equals("84.18");
        System.out.printf("Potential Profit USD: $%s\n", profit);
    }
}
