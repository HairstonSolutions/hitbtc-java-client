package com.hairstonsolutions.trading.clients.hitbtc.tests.api.ordering;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.ordering.OrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class TestOrderRestClient {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void getOrderByClientIdString() {
        String clientOrderId = "9dde33f8-afbb-4d3f-a182-6fa90dc";

        OrderRestClient orderRestClient = new OrderRestClient(hitBtcAPI);

        ResponseEntity<String> responseEntity = orderRestClient.getOrderStringByClientId(clientOrderId);

        assert responseEntity.getStatusCode().toString().equals("200 OK");
        System.out.println(responseEntity.getStatusCode().toString());

        String orderResponse = responseEntity.getBody();
        System.out.println(orderResponse);
    }

    @Test
    public void getOrderByClientIdObject() {
        String clientOrderId = "9dde33f8-afbb-4d3f-a182-6fa90dc";

        Order orderResponse = OrderRestClient.getOrderByClientId(clientOrderId, hitBtcAPI);
        System.out.println(orderResponse);
    }

    @Test
    public void getAllOpenOrders() {
        Order[] openOrders = OrderRestClient.getOpenOrders(hitBtcAPI);

        int count = 0;
        for ( Order orders : openOrders ) {
            System.out.println(orders);
            count++;
        }
        System.out.println(String.format("Orders Total: %s", count));
    }
}
