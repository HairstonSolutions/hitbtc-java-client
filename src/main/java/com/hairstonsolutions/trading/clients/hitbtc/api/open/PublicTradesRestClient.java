package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.trades.PublicTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PublicTradesRestClient {

    private static Logger LOG = LoggerFactory.getLogger(TickerRestClient.class);
    private static final String RESOURCE_PATH = "/public/trades";
    private static String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;


    public static PublicTrade[] getPublicTrades(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PublicTrade[]> responseEntity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}", PublicTrade[].class, tickerId);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }

    public static PublicTrade[] getPublicTrades(String tickerId, int amount) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PublicTrade[]> responseEntity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}?limit={amountToRetrieve}", PublicTrade[].class, tickerId, amount);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }
}
