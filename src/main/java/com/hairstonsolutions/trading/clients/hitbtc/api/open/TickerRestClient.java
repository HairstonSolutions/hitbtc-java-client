package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TickerRestClient {

    private static final String RESOURCE_PATH = "/public/ticker";
    private static final String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    private static final Logger LOG = LoggerFactory.getLogger(TickerRestClient.class);

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
        return getTickerById(tickerId);
    }

    public static Ticker getTickerById(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        Ticker ticker = restTemplate.getForObject(REQUEST_URI + "/{tickerId}", Ticker.class, tickerId);
        LOG.info(String.format("Return Values: %s", ticker.toString()));

        return ticker;
    }

    public static String getMarketBuyQuantityByUSDAmount(float usdAmount, String symbol) {
        Ticker myTicker = TickerRestClient.getTickerById(symbol);
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(usdAmount / Float.valueOf(myTicker.getAsk()));
    }

    public static String getMarketSellQuantityByUSDAmount(float usdAmount, String symbol) {
        Ticker myTicker = TickerRestClient.getTickerById(symbol);
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(usdAmount / Float.valueOf(myTicker.getBid()));
    }

    public static String getCurrentPrice(String symbol) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);
        return ticker.getAsk();
    }
}
