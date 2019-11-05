package com.hairstonsolutions.trading.clients.hitbtc.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HistoricalOrderRestClient {

    private static final String RESOURCE_PATH = "/history/order";
    private static final String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    private static Logger LOG = LoggerFactory.getLogger(HistoricalOrderRestClient.class);

    private HitBtcAPI hitBtcAPI;

    public HistoricalOrderRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public static Order[] getHistoricalOrders(HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Status Code: %s", responseEntity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }

    public static Order[] getHistoricalOrders(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit="+count, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Status Code: %s", responseEntity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }
}
