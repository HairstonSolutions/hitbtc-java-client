package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.open.TickerRestClient;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class TestTickerRestClient {

    @Test
    public void getBTCUSDTicker() {
        String tickerSymbol = "BTCUSD";
        TickerRestClient tickerRestClient = new TickerRestClient();

        ResponseEntity<Ticker> myGetTickerResponseEntity = tickerRestClient.getForEntity(tickerSymbol);

        assert myGetTickerResponseEntity.getStatusCode().toString().equals("200 OK");

        Ticker myTicker = myGetTickerResponseEntity.getBody();

        assert myTicker != null;
        assert myTicker.getSymbol().equals("BTCUSD");
    }

    @Test
    public void getTickerObjectByTickerID() {
        TickerRestClient tickerRestClient = new TickerRestClient();

        Ticker myTicker = tickerRestClient.getTicker("BTCUSD");

        System.out.println(myTicker);

        assert myTicker.getSymbol().equals("BTCUSD");
    }

    @Test
    public void getTickerObject() {
        Ticker myTicker = TickerRestClient.getTickerById("BTCUSD");

        System.out.println(myTicker);

        assert myTicker.getSymbol().equals("BTCUSD");
    }

    @Test
    public void getCurrentPrice() {
        String symbol = "BTCUSD";
        Ticker myTicker = TickerRestClient.getTickerById(symbol);

        System.out.println(myTicker);
        assert myTicker.getSymbol().equals("BTCUSD");
        System.out.printf("The Current %s price is: $%s", symbol, myTicker.getAsk());
    }

    @Test
    public void getCurrentPrice2() {
        String symbol = "BTCUSD";
        System.out.printf("The Current %s price is: $%s", symbol, TickerRestClient.getCurrentPrice(symbol));
    }

    @Test
    public void marketQuantity() {
        float marketAmount = 10.4f;
        String symbol = "BTCUSD";
        System.out.println(TickerRestClient.getMarketBuyQuantityByUSDAmount(marketAmount, symbol));
    }

    @Test
    public void marketQuantity2() {
        String symbol = "BTCUSD";
        float marketAmount = 10.4f;
        Ticker myTicker = TickerRestClient.getTickerById(symbol);

        System.out.println(myTicker);
        assert myTicker.getSymbol().equals("BTCUSD");

        System.out.printf("The Current %s price is: $%s\n", symbol, myTicker.getAsk());

        float calculatedQuantify = marketAmount / Float.parseFloat(myTicker.getAsk());
        System.out.printf("Final Quantity: %s", calculatedQuantify);
    }
}
