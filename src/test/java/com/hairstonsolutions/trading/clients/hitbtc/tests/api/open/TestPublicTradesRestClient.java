package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.api.open.PublicTradesRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.junit.Test;

import java.util.List;

public class TestPublicTradesRestClient {

    @Test
    public void getPublicTradesforSymbol() {
        String tickerId = "BTCUSD";

        List<PublicTrade> publicTrade = PublicTradesRestClient.getPublicTrades(tickerId);

        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, publicTrade.size()));
    }

    @Test
    public void getPublicTradesforSymbolByAmount() {
        String tickerId = "BTCUSD";
        int amountToRetrieve = 2;
        List<PublicTrade> publicTrade = PublicTradesRestClient.getPublicTrades(tickerId,amountToRetrieve);

        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, publicTrade.size()));
    }
}
