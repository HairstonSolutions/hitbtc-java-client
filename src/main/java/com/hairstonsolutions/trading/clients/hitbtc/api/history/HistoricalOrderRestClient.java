package com.hairstonsolutions.trading.clients.hitbtc.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricalOrderRestClient {

    private static final String RESOURCE_PATH = "/history/order";
    private static final String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    private static final Logger LOG = LoggerFactory.getLogger(HistoricalOrderRestClient.class);

    private HitBtcAPI hitBtcAPI;

    public HistoricalOrderRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public static Order[] getHistoricalOrders(HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }

    public static Order[] getHistoricalOrders(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit="+count, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }

    public static Trade[] getHistoricalTradesByOrderId(HitBtcAPI hitBtcAPI, long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI+"/"+orderId+"/trades", HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }

    public static Order getHistoricalOrder(HitBtcAPI hitBtcAPI, String clientOrderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI+"?clientOrderId="+clientOrderId, HttpMethod.GET, httpEntity, Order[].class);

        Order[] orders = responseEntity.getBody();

        assert orders != null;
        return orders[0];
    }

    public static List<Order> getHistoricalOrdersList(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit="+count, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        Order[] orderArray = responseEntity.getBody();

        List<Order> orderList = new ArrayList<>();

        assert orderArray != null;
        Collections.addAll(orderList, orderArray);

        return orderList;

    }
}
