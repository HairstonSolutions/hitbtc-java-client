package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.api.open.PublicTradesRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestPublicTradesRestClient {

    @Test
    public void getPublicTradesforSymbol() {
        String tickerId = "BTCUSD";

        PublicTrade[] publicTrade = PublicTradesRestClient.getPublicTrades(tickerId);

        assert publicTrade != null;

        int count = 0;
        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
            count++;
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, count));
    }

    @Test
    public void getPublicTradesforSymbolByAmount() {
        String tickerId = "BTCUSD";
        int amountToRetrieve = 2;
        PublicTrade[] publicTrade = PublicTradesRestClient.getPublicTrades(tickerId,amountToRetrieve);

        assert publicTrade != null;

        int count = 0;
        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
            count++;
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, count));
    }
}
