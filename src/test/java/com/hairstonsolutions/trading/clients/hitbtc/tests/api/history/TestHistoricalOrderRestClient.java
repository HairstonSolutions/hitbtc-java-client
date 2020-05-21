package com.hairstonsolutions.trading.clients.hitbtc.tests.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

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
        List<Order> historicalOrders = HistoricalOrderRestClient.getHistoricalOrders(hitBtcAPI);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }

    @Test
    public void getHistoricalOrdersByCount() {
        int number = 10;

        List<Order> historicalOrders = HistoricalOrderRestClient.getHistoricalOrders(hitBtcAPI, number);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }

    @Test
    public void getHistoricalTradesByHistoricalOrderID() {
        long orderId = 169111509127L;

        List<Trade> historicalTrades = HistoricalOrderRestClient.getHistoricalTradesByOrderId(hitBtcAPI, orderId);

        for (Trade trades : historicalTrades) {
            System.out.println(trades);
        }
        System.out.println(String.format("Order Trades Total: %s", historicalTrades.size()));
    }

    @Test
    public void historicalTradesPullForOrder() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOptionalHistoricalOrder(hitBtcAPI, clientOrderId);

        order.ifPresent(value -> value.setTradesReport(HistoricalOrderRestClient.pullOrderTradeReport(hitBtcAPI, value)));

        if (order.isPresent()) {
            assert !order.get().getTradesReport().isEmpty();

            int count = 0;
            for (Trade trades : order.get().getTradesReport()) {
                System.out.println(trades);
                count++;
            }
            System.out.println(String.format("Order Trades Total: %s", count));
            System.out.println(order);
        }
    }

    @Test
    public void historicalTradesPullWithinAnOrder() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOptionalHistoricalOrder(hitBtcAPI, clientOrderId);

        order.ifPresent(value -> value.pullTradesReport(hitBtcAPI));

        if (order.isPresent()) {
            assert order.get().getTradesReport() != null;

            int count = 0;
            for (Trade trades : order.get().getTradesReport()) {
                System.out.println(trades);
                count++;
            }
            System.out.println(String.format("Order Trades Total: %s", count));
            System.out.println(order);
        }
    }

    @Test
    public void getHistoricalOrderByClientOrderID() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOptionalHistoricalOrder(hitBtcAPI, clientOrderId);

        order.ifPresent(System.out::println);
        assert order.isEmpty() || order.get().getClientOrderId().equals(clientOrderId);
    }

    @Test
    public void getEmptyHistoricalOrderByClientOrderID() {
        String clientOrderId = "c992694fe26cac96c4417a9a272a39d8";
        Order order = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, clientOrderId);

        System.out.println(order);
        assert order == null;
    }

    @Test
    public void getOptionalHistoricalOrderByClientOrderID() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOptionalHistoricalOrder(hitBtcAPI, clientOrderId);

        order.ifPresent(System.out::println);
        assert order.isPresent();
    }

    @Test
    public void getEmptyOptionalHistoricalOrderByClientOrderID() {
        String clientOrderId = "c992694fe26cac96c4417a9a272a39d8";
        Optional<Order> order = HistoricalOrderRestClient.getOptionalHistoricalOrder(hitBtcAPI, clientOrderId);

        order.ifPresent(System.out::println);
        assert order.isEmpty();
    }

    @Test
    public void getHistoricalOrdersListByCount() {
        int number = 10;

        List<Order> historicalOrders = HistoricalOrderRestClient.getHistoricalOrdersList(hitBtcAPI, number);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }
}
