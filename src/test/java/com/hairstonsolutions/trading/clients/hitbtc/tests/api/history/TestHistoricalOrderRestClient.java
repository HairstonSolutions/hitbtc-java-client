package com.hairstonsolutions.trading.clients.hitbtc.tests.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;
import java.util.Optional;

public class TestHistoricalOrderRestClient {
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
    public void getHistoricalOrderByClientOrderID() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientOrderId);

        order.ifPresent(System.out::println);
        assert order.isEmpty() || order.get().getClientOrderId().equals(clientOrderId);
    }

    @Test
    public void getEmptyHistoricalOrderByClientOrderID() {
        String clientOrderId = "c992694fe26cac96c4417a9a272a39d8";
        Optional<Order> order = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientOrderId);

        order.ifPresent(System.out::println);
        assert order.isEmpty();
    }

    @Test
    public void getHistoricalOrders() {
        List<Order> historicalOrders = HistoricalOrderRestClient.getOrders(hitBtcAPI);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
        assert historicalOrders.size() > 0;
    }

    @Test
    public void getHistoricalOrdersByCount() {
        int number = 10;
        List<Order> historicalOrders = HistoricalOrderRestClient.getOrders(hitBtcAPI, number);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
        assert count == number;
    }

    @Test
    public void getHistoricalOrdersBySymbol() {
        String symbol = "BTCUSDC";
        int amountToPull = 50;
        List<Order> orders = HistoricalOrderRestClient.getOrdersBySymbol(hitBtcAPI, symbol, amountToPull);

        int count=0;
        for (Order order : orders) {
            System.out.print(order.getSymbol() + ", ");
            count++;
        }

        System.out.println();
        System.out.println(orders);
        System.out.println("Count: " + count);
        assert !orders.isEmpty();
        assert count <= amountToPull;
    }

    @Test
    public void getHistoricalTradesByHistoricalOrderID() {
        long orderId = 169111509127L;
        List<Trade> historicalTrades = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        for (Trade trades : historicalTrades) {
            System.out.println(trades);
            assert trades.getOrderId() == orderId;
        }
        System.out.println(String.format("Order Trades Total: %s", historicalTrades.size()));
    }

    @Test
    public void historicalTradesPullForOrder() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        Optional<Order> order = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientOrderId);

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
        Optional<Order> order = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientOrderId);

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
}
