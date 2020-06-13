package com.hairstonsolutions.trading.clients.hitbtc.tests.api.ordering;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.ordering.OrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class TestOrderRestClient {

    final String TEST_CONFIG_FILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TEST_CONFIG_FILE);
    }

    @Test
    public void getOrderByClientIdObject() {
        String clientOrderId = "9dde33f8-afbb-4d3f-a182-6fa90dc";

        Optional<Order> orderResponse = OrderRestClient.getOpenOrderByClientId(hitBtcAPI, clientOrderId);

        if (orderResponse.isPresent()) {
            System.out.printf("Open Order %s was Found:\n", clientOrderId);
            orderResponse.ifPresent(System.out::println);
        }
        else {
            System.out.printf("Order %s was not found as an open order.\n", clientOrderId);
        }
    }

    @Test
    public void getAllOpenOrders() {
        List<Order> openOrders = OrderRestClient.getOpenOrders(hitBtcAPI);

        for (Order order : openOrders) {
            System.out.println(order);
        }
        System.out.println(String.format("Orders Total: %s", openOrders.size()));
    }

    @Test
    public void getOpenOrdersBySymbol() {
        String symbol = "BTCUSD";
        List<Order> openOrders = OrderRestClient.getOpenOrders(hitBtcAPI, symbol);

        for (Order order : openOrders) {
            System.out.println(order);
            assert order.getSymbol().equals(symbol);
        }
        System.out.println(String.format("Orders Total: %s", openOrders.size()));
    }

    @Ignore
    @Test
    public void postLimitBuyOrder() {
        String symbol = "BTCUSD";
        String side = Side.BUY;
        String quantity = "0.00130";
        String price = "9002.16";

        Optional<Order> orderReponse = OrderRestClient.sendLimitOrder(
                hitBtcAPI, symbol, side, quantity, price);

        orderReponse.ifPresent(System.out::println);
    }

    @Ignore
    @Test
    public void buyMarketOrder() {
        String symbol = "BTCUSD";
        String amount = "15.00";

        Optional<Order> myMarketOrder = OrderRestClient.sendMarketBuyOrder(hitBtcAPI, symbol, amount);

        myMarketOrder.ifPresent(System.out::println);
        assert myMarketOrder.isEmpty() || !myMarketOrder.get().getTradesReport().isEmpty();
    }

    @Ignore
    @Test
    public void sellMarketOrder() {
        String symbol = "BTCUSD";
        String amount = "21.00";

        Optional<Order> myMarketOrder = OrderRestClient.sendMarketSellOrder(hitBtcAPI, symbol, amount);

        myMarketOrder.ifPresent(System.out::println);
        assert myMarketOrder.isEmpty() || !myMarketOrder.get().getTradesReport().isEmpty();
    }

    @Ignore
    @Test
    public void marketLikeBuyOrder() {
        String symbol = "BTCUSDC";
        String amount = "15.00";

        Optional<Order> myLikeMarketOrder = OrderRestClient.sendLikeMarketBuyOrder(hitBtcAPI, symbol, amount);

        myLikeMarketOrder.ifPresent(System.out::println);
        assert (myLikeMarketOrder.isPresent());
    }

    @Ignore
    @Test
    public void marketLikeSellOrder() {
        String symbol = "BTCUSDC";
        String amount = "15.00";

        Optional<Order> myLikeMarketOrder = OrderRestClient.sendLikeMarketSellOrder(hitBtcAPI, symbol, amount);

        myLikeMarketOrder.ifPresent(System.out::println);
        assert myLikeMarketOrder.isPresent();
    }

    @Test
    public void stopLimitOrder() {

    }

    @Test
    public void stopMarketOrder() {

    }
}
