package com.hairstonsolutions.trading.clients.hitbtc.api.history;

import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Optional;

public class HistoricalOrderRestClient {

    private static final String RESOURCE_PATH = "/history/order";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(HistoricalOrderRestClient.class);

    public static Optional<Order> getOrderByClientOrderId(HitBtcAPI hitBtcAPI, String clientOrderId) {
        String uri = REQUEST_URI + "?clientOrderId=" + clientOrderId;
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> order = apiAuthRequest.getItemRequest(uri, Order[].class);
        if (order.isEmpty())
            LOG.info(String.format("Response Successful, but Empty []. The requested clientOrderID %s might be Incorrect, Cancelled, Expired, still an Open order or Inaccessible to this account.", clientOrderId));
        return order;
    }

    public static List<Order> getOrders(HitBtcAPI hitBtcAPI) {
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        return apiAuthRequest.getListRequest(REQUEST_URI, Order[].class);
    }

    public static List<Order> getOrders(HitBtcAPI hitBtcAPI, int count) {
        String uri = REQUEST_URI + "?limit=" + count;
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        return apiAuthRequest.getListRequest(uri, Order[].class);
    }

    public static List<Order> getOrdersBySymbol(HitBtcAPI hitBtcAPI, String symbol, int count) {
        String uri = REQUEST_URI + "?symbol=" + symbol + "&limit=" + count;
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        return apiAuthRequest.getListRequest(uri, Order[].class);
    }

    public static List<Trade> getTradesByOrderId(HitBtcAPI hitBtcAPI, long orderId) {
        String uri = REQUEST_URI + "/" + orderId + "/trades";
        ApiAuthRequest<Trade> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        return apiAuthRequest.getListRequest(uri, Trade[].class);
    }

    public static List<Trade> pullOrderTradeReport(HitBtcAPI hitBtcAPI, Order order) {
        String uri = REQUEST_URI + "/" + order.getId() + "/trades";
        ApiAuthRequest<Trade> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        return apiAuthRequest.getListRequest(uri, Trade[].class);
    }
}
