package com.hairstonsolutions.trading.clients.hitbtc.tests.api;

import com.hairstonsolutions.trading.clients.hitbtc.account.Direction;
import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

public class ApiAuthRequestTest {
    final String TEST_CONFIG_FILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TEST_CONFIG_FILE);
    }

    @Test
    public void singleItemAsOptionalHistoricalOrder() {
        String clientOrderId = "84c5b2b6a1dc0ad12d257a5ce77cf58f";
        String resourcePath = "/history/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath + "?clientOrderId=" + clientOrderId;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> order = apiAuthRequest.getItemRequest(uri, Order[].class);

        order.ifPresent(System.out::println);
        assert order.isPresent();
    }

    @Test
    public void trueSingleItemAsOptionalHistoricalOrder() {
        String clientOrderId = "9dde33f8-afbb-4d3f-a182-6fa90dc";
        String resourcePath = "/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath + "/" + clientOrderId;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> openOrder = apiAuthRequest.getTrueItemRequest(uri, Order.class);

        if (openOrder.isPresent()) {
            System.out.printf("Open Order %s was Found:\n", clientOrderId);
            openOrder.ifPresent(System.out::println);
        }
        else {
            System.out.printf("Order %s was not found as an open order.\n", clientOrderId);
        }
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
        assert historicalOrders.size() > 0;
    }

    @Ignore
    @Test
    public void transfer() {
        String resourcePath = "/account/transfer";
        String uri = HitBtcAPI.BASE_URL + resourcePath;

        String direction = Direction.TO_MAIN_BANK;
        String currency = "USD";
        String amount = "1.0";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("type", direction);
        map.add("currency", currency);
        map.add("amount", amount);

        ApiAuthRequest<TransferResponse> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<TransferResponse> response = apiAuthRequest.postRequest(uri, map, TransferResponse.class);

        response.ifPresent(System.out::println);
        assert response.isPresent();
    }

    @Ignore
    @Test
    public void deleteAnOpenOrder() {
        String openOrderClientId = "7ff6dc44aed32a671367c2bca835474b";
        String resourcePath = "/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath + "/" + openOrderClientId;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> deletedOrder = apiAuthRequest.deleteRequest(uri, Order.class);

        if (deletedOrder.isPresent()) {
            System.out.printf("Open Order %s was Found and Deleted:\n", openOrderClientId);
            deletedOrder.ifPresent(System.out::println);
        }
        else {
            System.out.printf("Order %s was not found as an open order to be deleted.\n", openOrderClientId);
        }
    }

    @Ignore
    @Test
    public void deleteAllOpenOrders() {
        String resourcePath = "/order";
        String uri = HitBtcAPI.BASE_URL + resourcePath;

        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Order> deletedOrders = apiAuthRequest.deleteMultipleRequest(uri, Order[].class);

        for (Order deletedOrder : deletedOrders) {
            System.out.printf("Open Order %s was Found and Deleted:\n", deletedOrder.getClientOrderId());
            System.out.println(deletedOrder);
        }
    }
}
