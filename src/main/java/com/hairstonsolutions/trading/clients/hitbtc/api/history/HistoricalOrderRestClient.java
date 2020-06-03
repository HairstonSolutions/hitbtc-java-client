package com.hairstonsolutions.trading.clients.hitbtc.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
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
import java.util.Optional;

public class HistoricalOrderRestClient {

    private static final String RESOURCE_PATH = "/history/order";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(HistoricalOrderRestClient.class);

    private HitBtcAPI hitBtcAPI;

    public HistoricalOrderRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public static List<Order> getHistoricalOrders(HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Order> getHistoricalOrders(HitBtcAPI hitBtcAPI, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit=" + count, HttpMethod.GET, httpEntity, Order[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTradesByOrderId(HitBtcAPI hitBtcAPI, long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "/" + orderId + "/trades", HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> getHistoricalTradesListByOrderId(HitBtcAPI hitBtcAPI, long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "/" + orderId + "/trades", HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static List<Trade> pullOrderTradeReport(HitBtcAPI hitBtcAPI, Order order) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Trade[]> responseEntity = restTemplate.exchange(REQUEST_URI + "/" + order.getId() + "/trades", HttpMethod.GET, httpEntity, Trade[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static Order getHistoricalOrder(HitBtcAPI hitBtcAPI, String clientOrderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?clientOrderId=" + clientOrderId, HttpMethod.GET, httpEntity, Order[].class);

        if (responseEntity.getStatusCodeValue() != 200)
            LOG.error(String.format("Requesting Historical Order Failed. HTTP Response: %s", responseEntity.toString()));

        LOG.debug(String.format("Return Status Code: %s", responseEntity.getStatusCode()));
        if (responseEntity.getBody() != null)
            LOG.debug(String.format("Return Values: %s", responseEntity.getBody().toString()));

        List<Order> order = Arrays.asList((responseEntity.getBody()));

        if(!order.isEmpty())
            return order.get(0);
        else {
            LOG.info(String.format("Response Successful, but Empty []. The requested clientOrderID %s might be Incorrect, Cancelled, Expired, still an Open order or Inaccessible to this account.", clientOrderId));
            return null;
        }
    }

    public static Optional<Order> getOptionalHistoricalOrder(HitBtcAPI hitBtcAPI, String clientOrderId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?clientOrderId=" + clientOrderId, HttpMethod.GET, httpEntity, Order[].class);

        if (responseEntity.getStatusCodeValue() != 200) {
            LOG.error(String.format("Requesting Historical Order Failed. HTTP Response: %s", responseEntity.toString()));
            return Optional.empty();
        }

        LOG.debug(String.format("Return Status Code: %s", responseEntity.getStatusCode()));
        if (responseEntity.getBody() != null)
            LOG.debug(String.format("Return Values: %s", responseEntity.getBody().toString()));

        List<Order> order = Arrays.asList((responseEntity.getBody()));

        if (order.isEmpty()) {
            LOG.info(String.format("Response Successful, but Empty []. The requested clientOrderID %s might be Incorrect, Cancelled, Expired, still an Open order or Inaccessible to this account.", clientOrderId));
            return Optional.empty();
        }
        else
            return Optional.ofNullable(order.get(0));
    }

    public static Optional<List<Order>> getOptionalOrdersbySymbol(HitBtcAPI hitBtcAPI, String symbol, int count) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Order[]> responseEntity = restTemplate.exchange(REQUEST_URI + "?limit=" + count + "?symbol=" + symbol

                , HttpMethod.GET, httpEntity, Order[].class);

        if (responseEntity.getStatusCodeValue() != 200) {
            LOG.error(String.format("Requesting Historical Order Failed. HTTP Response: %s", responseEntity.toString()));
            return Optional.empty();
        }

        LOG.debug(String.format("Return Status Code: %s", responseEntity.getStatusCode()));
        if (responseEntity.getBody() != null)
            LOG.debug(String.format("Return Values: %s", responseEntity.getBody().toString()));

        List<Order> order = Arrays.asList((responseEntity.getBody()));

        if (order.isEmpty()) {
            LOG.info(String.format("Response Successful, but Empty []. The requested clientOrderID %s might be Incorrect, Cancelled, Expired, still an Open order or Inaccessible to this account.", symbol));
            return Optional.empty();
        }
        else
            return Optional.of(order);
    }
}
