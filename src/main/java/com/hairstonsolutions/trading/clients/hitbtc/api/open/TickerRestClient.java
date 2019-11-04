package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TickerRestClient {

    private static Logger LOG = LoggerFactory.getLogger(TickerRestClient.class);
    private static final String RESOURCE_PATH = "/public/ticker";
    private static String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;

    public TickerRestClient() {
    }

    public ResponseEntity<Ticker> getForEntity(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ticker> entity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}",
                Ticker.class, tickerId);
        LOG.info(String.format("Status Code: %s", entity.getStatusCode()));
        LOG.info(String.format("Return Values: %s", entity.toString()));

        return entity;
    }

    public Ticker getTicker(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        Ticker ticker = restTemplate.getForObject(REQUEST_URI + "/{tickerId}", Ticker.class, tickerId);
        LOG.info(String.format("Return Values: %s", ticker.toString()));

        return ticker;
    }
}
