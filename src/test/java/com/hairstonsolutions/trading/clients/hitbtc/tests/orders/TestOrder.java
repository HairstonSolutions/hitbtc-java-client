package com.hairstonsolutions.trading.clients.hitbtc.tests.orders;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestOrder {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void composeTradeReports() {
        int numberToRetrieve = 10;

        List<Order> retrievedHistoricalOrders = HistoricalOrderRestClient.getHistoricalOrders(hitBtcAPI, numberToRetrieve);

        for (Order order : retrievedHistoricalOrders) {
            order.pullTradesReport(hitBtcAPI);
            System.out.println(order);
        }
    }

    @Test
    public void composeSingleTradeReport() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Order retrievedHistoricalOrder = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, clientID);

        retrievedHistoricalOrder.pullTradesReport(hitBtcAPI);

        List<Trade> trades = retrievedHistoricalOrder.getTradesReport();
        System.out.println(trades);
    }

    @Test
    public void setIncompleteTradeReportWithOrderData() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Order retrievedHistoricalOrder = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, clientID);

        retrievedHistoricalOrder.pullTradesReport(hitBtcAPI);
        System.out.println(retrievedHistoricalOrder);

        List<Trade> trades = retrievedHistoricalOrder.getTradesReport();
        System.out.println(trades);

        //Clear Pulled Data to simulate a Market Order Response
        for (Trade trade : trades) {
            trade.setClientOrderId("");
            trade.setOrderId(0);
            trade.setSymbol("");
            trade.setSide("sell");
        }
        System.out.println(trades);

        retrievedHistoricalOrder.reconcileRetrievedTradeReport();

        for (Trade trade : trades) {
            assert trade.getClientOrderId().equals(retrievedHistoricalOrder.getClientOrderId());
            assert trade.getOrderId() == retrievedHistoricalOrder.getId();
            assert trade.getSymbol().equals(retrievedHistoricalOrder.getSymbol());
            assert trade.getSide().equals(retrievedHistoricalOrder.getSide());
        }
        System.out.println(retrievedHistoricalOrder);
    }

    @Test
    public void setOrderPriceByTradesReportAveragePrice() {
        String clientID = "3ed8f7a1b81ce5ae0ecc56f508f448f1";
        Order retrievedHistoricalOrder = HistoricalOrderRestClient.getHistoricalOrder(hitBtcAPI, clientID);
        System.out.println(retrievedHistoricalOrder);
        System.out.println("Order Price Before Pull: " + retrievedHistoricalOrder.getPrice());

        retrievedHistoricalOrder.pullTradesReport(hitBtcAPI);
        System.out.println(retrievedHistoricalOrder);
        System.out.println("Order Price After Pull + Calculation: " + retrievedHistoricalOrder.getPrice());
    }

}
