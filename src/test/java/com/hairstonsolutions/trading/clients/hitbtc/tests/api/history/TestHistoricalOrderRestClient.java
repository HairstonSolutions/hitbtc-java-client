package com.hairstonsolutions.trading.clients.hitbtc.tests.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestHistoricalOrderRestClient {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void getAllHistoricalOrders() {
        Order[] historicalOrders = HistoricalOrderRestClient.getHistoricalOrders(hitBtcAPI);

        int count = 0;
        for ( Order orders : historicalOrders ) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }

    @Test
    public void getHistoricalOrdersByCount() {
        int number = 10;

        Order[] historicalOrders = HistoricalOrderRestClient.getHistoricalOrders(hitBtcAPI, number);

        int count = 0;
        for ( Order orders : historicalOrders ) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }

    @Test
    public void getHistoricalTradesByHistoricalOrderID() {
        long orderId = 169111509127L;

        Trade[] historicalTrades = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI, orderId);

        int count = 0;
        for ( Trade trades : historicalTrades ) {
            System.out.println(trades);
            count++;
        }
        System.out.println(String.format("Order Trades Total: %s", count));
    }

    @Test
    public void historicalTradesPullForOrder() {
        Order order = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, "84c5b2b6a1dc0ad12d257a5ce77cf58f");

        order.setTradesReport(HistoricalOrderRestClient.pullOrderTradeReport(hitBtcAPI, order));

        assert order.getTradesReport() != null;

        int count = 0;
        for ( Trade trades : order.getTradesReport() ) {
            System.out.println(trades);
            count++;
        }
        System.out.println(String.format("Order Trades Total: %s", count));
        System.out.println(order);
    }

    @Test
    public void historicalTradesPullWithinAnOrder() {
        Order order = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, "84c5b2b6a1dc0ad12d257a5ce77cf58f");

        order.pullTradesReport(hitBtcAPI);

        assert order.getTradesReport() != null;

        int count = 0;
        for ( Trade trades : order.getTradesReport() ) {
            System.out.println(trades);
            count++;
        }
        System.out.println(String.format("Order Trades Total: %s", count));
        System.out.println(order);
    }

    @Test
    public void getHistoricalOrderbyClientOrderID() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";

        Order order = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, clientOrderId);

        assert order.getClientOrderId().equals(clientOrderId);
        System.out.println(order);
    }

    @Test
    public void getHistoricalOrdersListByCount() {
        int number = 10;

        List<Order> historicalOrders = HistoricalOrderRestClient.getHistoricalOrdersList(hitBtcAPI, number);

        int count = 0;
        for ( Order orders : historicalOrders ) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }
}
