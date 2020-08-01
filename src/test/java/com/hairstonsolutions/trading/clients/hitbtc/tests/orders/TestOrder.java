package com.hairstonsolutions.trading.clients.hitbtc.tests.orders;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TestOrder {
    private HitBtcAPI hitBtcAPI;
    private AnnotationConfigApplicationContext context;
    private ConfigurableEnvironment env;

    @Before
    public void load() {
        context = new AnnotationConfigApplicationContext();
        env = context.getEnvironment();

        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.setApiKey(env.getProperty("TEST_HITBTC_API_KEY"));
        hitBtcAPI.setApiSecret(env.getProperty("TEST_HITBTC_API_SECRET"));
    }

    @Test
    public void composeTradeReports() {
        int numberToRetrieve = 10;

        List<Order> retrievedHistoricalOrders = HistoricalOrderRestClient.getOrders(hitBtcAPI, numberToRetrieve);

        for (Order order : retrievedHistoricalOrders) {
            order.pullTradesReport(hitBtcAPI);
            System.out.println(order);
        }
    }

    @Test
    public void composeSingleTradeReport() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Optional<Order> retrievedHistoricalOrder = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientID);

        retrievedHistoricalOrder.ifPresent(order -> order.pullTradesReport(hitBtcAPI));
        if (retrievedHistoricalOrder.isPresent()) {
            List<Trade> trades = retrievedHistoricalOrder.get().getTradesReport();
            System.out.println(trades);
            assert !trades.isEmpty();
            assert trades.get(0).getClientOrderId().equals(clientID);
        }
    }

    @Test
    public void setIncompleteTradeReportWithOrderData() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Optional<Order> retrievedHistoricalOrder = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientID);

        retrievedHistoricalOrder.ifPresent(order -> order.pullTradesReport(hitBtcAPI));
        retrievedHistoricalOrder.ifPresent(System.out::println);

        List<Trade> trades = new LinkedList<>();

        if (retrievedHistoricalOrder.isPresent()) {
            trades = retrievedHistoricalOrder.get().getTradesReport();
            System.out.println(trades);

            //Clear Pulled Data to simulate a Market Order Response
            for (Trade trade : trades) {
                trade.setClientOrderId("");
                trade.setOrderId(0);
                trade.setSymbol("");
                trade.setSide("sell");
            }
            System.out.println(trades);
        }

        retrievedHistoricalOrder.ifPresent(Order::reconcileRetrievedTradeReport);

        if (retrievedHistoricalOrder.isPresent()) {
            for (Trade trade : trades) {
                assert trade.getClientOrderId().equals(retrievedHistoricalOrder.get().getClientOrderId());
                assert trade.getOrderId() == retrievedHistoricalOrder.get().getId();
                assert trade.getSymbol().equals(retrievedHistoricalOrder.get().getSymbol());
                assert trade.getSide().equals(retrievedHistoricalOrder.get().getSide());
            }
        }
        retrievedHistoricalOrder.ifPresent(System.out::println);
    }

    @Test
    public void setOrderPriceByTradesReportAveragePrice() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Optional<Order> retrievedHistoricalOrder = HistoricalOrderRestClient.getOrderByClientOrderId(hitBtcAPI, clientID);
        retrievedHistoricalOrder.ifPresent(System.out::println);

        if (retrievedHistoricalOrder.isPresent()) {
            System.out.println("Order Price Before Pull: " + retrievedHistoricalOrder.get().getPrice());

            retrievedHistoricalOrder.get().pullTradesReport(hitBtcAPI);
            System.out.println(retrievedHistoricalOrder);
            System.out.println("Order Price After Pull + Calculation: " + retrievedHistoricalOrder.get().getPrice());
        }
    }
}
