package com.hairstonsolutions.trading.clients.hitbtc.tests.api;

import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class ApiAuthRequestTest {
    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void singleItemAsOptionalHistoricalOrder() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        String resourcePath = "/history/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath + "?clientOrderId=" + clientOrderId;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> order = apiAuthRequest.getItemRequest(uri, Order[].class);

        order.ifPresent(System.out::println);
    }

    @Test
    public void listItemsAsHistoricalOrders() {
        String resourcePath = "/history/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Order> historicalOrders = apiAuthRequest.getListRequest(uri, Order[].class);

        int count = 0;
        for (Order orders : historicalOrders) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }
}
