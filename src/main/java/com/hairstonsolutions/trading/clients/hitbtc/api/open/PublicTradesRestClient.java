package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PublicTradesRestClient {

    Logger LOG = LoggerFactory.getLogger(TickerRestClient.class);

    private static final String RESOURCE_PATH = "/public/trades";

    private String REQUEST_URI;
    private RestTemplate restTemplate;

    public PublicTradesRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    }

    public ResponseEntity<PublicTrade[]> getForEntity(String tickerId) {
        ResponseEntity<PublicTrade[]> entity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}", PublicTrade[].class, tickerId);

        LOG.info(String.format("Status Code: %s", entity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", entity.toString()));

        return entity;
    }

    public ResponseEntity<PublicTrade[]> getForEntity(String tickerId, int amountToRetrieve) {
        ResponseEntity<PublicTrade[]> entity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}?limit={amountToRetrieve}", PublicTrade[].class, tickerId, amountToRetrieve);

        LOG.info(String.format("Status Code: %s", entity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", entity.toString()));

        return entity;
    }
}
