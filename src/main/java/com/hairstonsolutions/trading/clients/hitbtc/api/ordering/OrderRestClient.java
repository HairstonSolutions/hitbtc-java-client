package com.hairstonsolutions.trading.clients.hitbtc.api.ordering;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.open.TickerRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class OrderRestClient {

    private static final String RESOURCE_PATH = "/order";
    private static final String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;
    private static final Logger LOG = LoggerFactory.getLogger(OrderRestClient.class);

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

    public static Order[] getOpenOrders(HitBtcAPI hitBtcAPI) {
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

    public static Order sendLimitOrder(HitBtcAPI hitBtcAPI, String symbol, Side side, String quantity, String price) {
        TradeType tradeType = new TradeType(TradeType.LIMIT);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);
        boolean postOnly = false;

        Order limitOrder = OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, postOnly);

        return limitOrder;
    }

    public static Order sendMarketBuyOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendMarketOrder(hitBtcAPI, symbol, amount, new Side(Side.BUY));
    }

    public static Order sendMarketSellOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendMarketOrder(hitBtcAPI, symbol, amount, new Side(Side.SELL));
    }

    public static Order sendMarketOrder(HitBtcAPI hitBtcAPI, String symbol, String amount, Side side) {
        Ticker ticker = TickerRestClient.getTickerById(symbol.toString());
        String price = ticker.getLast();
        String quantity="";

        switch (side.getSide()){
            case "buy":
                quantity = ticker.getMarketBuyQuantityByAmount(Float.valueOf(amount));
                break;
            case "sell":
                quantity = ticker.getMarketSellQuantityByAmount(Float.valueOf(amount));
                break;
        }

        TradeType tradeType = new TradeType(TradeType.MARKET);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.IOC_IMMEDIATE_OR_CANCEL);
        boolean postOnly = false;

        Order marketOrder = OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, postOnly);

        return marketOrder;
    }

    public static Order sendOrder(HitBtcAPI hitBtcAPI, String symbol, Side side, String quantity, String price,
                                  TradeType tradeType, TimeInForce timeInForce, boolean postOnly) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedCredentials);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("symbol", symbol);
        map.add("side", side.toString());
        map.add("type", tradeType.toString());
        map.add("timeInForce", timeInForce.toString());
        map.add("quantity", quantity);
        map.add("price", price);
        if (postOnly)
            map.add("postOnly", "true");
        else map.add("postOnly", "false");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Order> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.POST, request, Order.class);

        LOG.info(String.format("Submitted %s %s Order: Price=%s, Quantity=%s, Symbol=%s, TimeInForce=%s %s",
                tradeType.toString(), side.toString(), quantity, symbol, timeInForce.toString()));
        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }
}
