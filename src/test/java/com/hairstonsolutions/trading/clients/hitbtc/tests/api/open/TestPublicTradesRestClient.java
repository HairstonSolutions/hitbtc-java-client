package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.api.open.PublicTradesRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestPublicTradesRestClient {

    @Test
    public void getTradesForSymbol() {
        String tickerId = "BTCUSD";
        PublicTradesRestClient publicTradesRestClient = new PublicTradesRestClient(new RestTemplate());

        ResponseEntity<PublicTrade[]> myGetTradesResponseEntity = publicTradesRestClient.getForEntity(tickerId);

        assert myGetTradesResponseEntity.getStatusCode().toString().equals("200 OK");

        PublicTrade[] publicTrade = myGetTradesResponseEntity.getBody();

        int count = 0;
        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
            count++;
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, count));
    }

    @Test
    public void getTradesBySymbolAmount() {
        String tickerId = "BTCUSD";
        int amountToRetrieve = 23;
        PublicTradesRestClient publicTradesRestClient = new PublicTradesRestClient(new RestTemplate());

        ResponseEntity<PublicTrade[]> myGetTradesResponseEntity = publicTradesRestClient.getForEntity(tickerId, amountToRetrieve);

        assert myGetTradesResponseEntity.getStatusCode().toString().equals("200 OK");

        PublicTrade[] publicTrade = myGetTradesResponseEntity.getBody();

        int count = 0;
        for ( PublicTrade pb : publicTrade ) {
            System.out.println(pb);
            count++;
        }

        System.out.println(String.format("Public Trades Retrieved for Ticker %s : %s", tickerId, count));
    }
}
