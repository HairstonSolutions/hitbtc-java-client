package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

/*
THERE AREN'T MANY TEST IN THIS CLASS AS THERE ARE ONLY
GENERAL GETTERS AND SETTERS FOR THE TICKER OBJECT.
 */

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.open.TickerRestClient;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestTickerRestClient {

    @Test
    public void getBTCUSDTicker() {
        String tickerSymbol = "BTCUSD";
        TickerRestClient tickerRestClient = new TickerRestClient(new RestTemplate());

        ResponseEntity<Ticker> myGetTickerResponseEntity = tickerRestClient.getForEntity(tickerSymbol);

        assert myGetTickerResponseEntity.getStatusCode().toString().equals("200 OK");

        Ticker myTicker = myGetTickerResponseEntity.getBody();

        assert myTicker != null;
        assert myTicker.getSymbol().equals("BTCUSD");
    }

    @Test
    public void getTickerObjectByTickerID() {
        TickerRestClient tickerRestClient = new TickerRestClient(new RestTemplate());

        Ticker myTicker = tickerRestClient.getTicker("BTCUSD");

        System.out.println(myTicker);
    }
}
