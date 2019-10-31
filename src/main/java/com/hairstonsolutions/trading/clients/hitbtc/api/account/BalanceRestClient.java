package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BalanceRestClient {

    Logger LOG = LoggerFactory.getLogger(BalanceRestClient.class);

    private static final String RESOURCE_PATH = "/account/balance";

    private String REQUEST_URI;
    private RestTemplate restTemplate;
    HitBtcAPI hitBtcAPI;

    public BalanceRestClient(RestTemplate restTemplate, HitBtcAPI hitBtcAPI) {
        this.restTemplate = restTemplate;
        this.hitBtcAPI = hitBtcAPI;
        this.REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    }

    public ResponseEntity<Balance[]> getForEntity() {
        String encodedcredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Basic " + encodedcredentials);

        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

        ResponseEntity<Balance[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Balance[].class);

        LOG.info(String.format("Status Code: %s", responseEntity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity;
    }
}
