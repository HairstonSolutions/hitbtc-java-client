package com.hairstonsolutions.trading.clients.hitbtc.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HistoricalTradeRestClient {

    private static final String RESOURCE_PATH = "/history/trades";
    private static final String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(HistoricalTradeRestClient.class);

    public static List<Trade> getHistoricalTrades(HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTrades(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit="+count, HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTradesAscending(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?sort=ASC&limit="+count, HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTradesSymbol(HitBtcAPI hitBtcAPI, String symbol, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?symbol="+symbol+"&limit="+count, HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTradesSymbolAscending(HitBtcAPI hitBtcAPI, String symbol, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?symbol="+symbol+"&sort=ASC&limit="+count, HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

}
