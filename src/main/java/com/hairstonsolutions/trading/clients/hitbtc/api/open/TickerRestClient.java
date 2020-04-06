package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TickerRestClient {

    private static final String RESOURCE_PATH = "/public/ticker";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(TickerRestClient.class);

    public TickerRestClient() {
    }

    public ResponseEntity<Ticker> getForEntity(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ticker> responseEntity = restTemplate.getForEntity(REQUEST_URI + "/{tickerId}",
                Ticker.class, tickerId);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity;
    }

    public Ticker getTicker(String tickerId) {
        return getTickerById(tickerId);
    }

    public static Ticker getTickerById(String tickerId) {
        RestTemplate restTemplate = new RestTemplate();
        Ticker ticker = restTemplate.getForObject(REQUEST_URI + "/{tickerId}", Ticker.class, tickerId);

        assert ticker != null;
        LOG.info(String.format("Return Values: %s", ticker.toString()));

        return ticker;
    }

    public static String getMarketBuyQuantityByUSDAmount(float usdAmount, String symbol) {
        Ticker myTicker = TickerRestClient.getTickerById(symbol);
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);

        return df.format(usdAmount / Float.parseFloat(myTicker.getAsk()));
    }

    public static String getMarketSellQuantityByUSDAmount(float usdAmount, String symbol) {
        Ticker myTicker = TickerRestClient.getTickerById(symbol);
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);

        return df.format(usdAmount / Float.parseFloat(myTicker.getBid()));
    }

    public static String getCurrentPrice(String symbol) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);

        return ticker.getAsk();
    }
}
