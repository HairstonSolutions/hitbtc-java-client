package com.hairstonsolutions.trading.clients.hitbtc.api.ordering;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OrderRestClient {

    private static Logger LOG = LoggerFactory.getLogger(OrderRestClient.class);
    private static String RESOURCE_PATH = "/order";
    private static String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;

    private HitBtcAPI hitBtcAPI;

    public OrderRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public ResponseEntity<String> getOrderStringByClientId(String clientOrderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(REQUEST_URI + "/"+clientOrderId, HttpMethod.GET, httpEntity, String.class);

        LOG.info(String.format("Status Code: %s", responseEntity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity;
    }


    public static Order getOrderByClientId(String clientOrderId, HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order> responseEntity = restTemplate.exchange(REQUEST_URI + "/"+clientOrderId, HttpMethod.GET, httpEntity, Order.class);

        LOG.info(String.format("Status Code: %s", responseEntity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }


}
