package com.hairstonsolutions.trading.clients.hitbtc.tests.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalTradeRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class TestHistoricalTradeRestClient {
    private HitBtcAPI hitBtcAPI;
    private AnnotationConfigApplicationContext context;
    private ConfigurableEnvironment env;

    @Before
    public void load() {
        context = new AnnotationConfigApplicationContext();
        env = context.getEnvironment();

        String apiKey = env.getProperty("TEST_HITBTC_API_KEY");
        String secretKey = env.getProperty("TEST_HITBTC_API_SECRET");
        hitBtcAPI = new HitBtcAPI(apiKey, secretKey);
    }

    @Test
    public void getAllHistoricalTrades() {
        List<Trade> historicalTrades = HistoricalTradeRestClient.getHistoricalTrades(hitBtcAPI);

        for (Trade trade : historicalTrades) {
            System.out.println(trade);
        }
        System.out.println("Total Count: " + historicalTrades.size());
    }

    @Test
    public void getHistoricalTradesByCount() {
        int number = 10;
        List<Trade> historicalTrades = HistoricalTradeRestClient.getHistoricalTrades(hitBtcAPI, number);

        for (Trade trade : historicalTrades) {
            System.out.println(trade);
        }
        System.out.println("Total Count: " + historicalTrades.size());
    }

    @Test
    public void getHistoricalTradesByCountAscending() {
        int number = 1000;
        List<Trade> historicalTrades = HistoricalTradeRestClient.getHistoricalTradesAscending(hitBtcAPI, number);

        for (Trade trade : historicalTrades) {
            System.out.println(trade);
        }
        System.out.println("Total Count: " + historicalTrades.size());
    }

    @Test
    public void getHistoricalTradesBySymbol() {
        int number = 100;
        String Symbol = "BTCUSD";
        List<Trade> historicalTrades = HistoricalTradeRestClient.getHistoricalTradesSymbol(hitBtcAPI, Symbol, number);

        for (Trade trade : historicalTrades) {
            System.out.println(trade);
        }
        System.out.println("Total Count: " + historicalTrades.size());
    }

    @Test
    public void getHistoricalTradesBySymbolAscending() {
        int number = 100;
        String Symbol = "BTCUSDC";
        List<Trade> historicalTrades = HistoricalTradeRestClient.getHistoricalTradesSymbolAscending(hitBtcAPI, Symbol, number);

        for (Trade trade : historicalTrades) {
            System.out.println(trade);
        }
        System.out.println("Total Count: " + historicalTrades.size());
    }
}
