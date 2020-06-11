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
        assert order.isPresent();
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
}
