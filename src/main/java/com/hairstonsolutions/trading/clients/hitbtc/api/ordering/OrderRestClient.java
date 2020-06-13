package com.hairstonsolutions.trading.clients.hitbtc.api.ordering;

import com.hairstonsolutions.trading.clients.hitbtc.Ticker;
import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.open.TickerRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import com.hairstonsolutions.trading.clients.hitbtc.attributes.TradeType;
import com.hairstonsolutions.trading.clients.hitbtc.orders.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

public class OrderRestClient {

    private static final String RESOURCE_PATH = "/order";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(OrderRestClient.class);

    public static Optional<Order> getOpenOrderByClientId(HitBtcAPI hitBtcAPI, String clientOrderId) {
        String uri = REQUEST_URI + "/" + clientOrderId;
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        Optional<Order> openOrder = apiAuthRequest.getTrueItemRequest(uri, Order.class);
        if (openOrder.isEmpty())
            LOG.error(String.format("Error Retrieving Open order %s: from API.", clientOrderId));
        return openOrder;
    }

    public static List<Order> getOpenOrders(HitBtcAPI hitBtcAPI) {
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Order> openOrders = apiAuthRequest.getListRequest(REQUEST_URI, Order[].class);
        if (openOrders.isEmpty())
            LOG.error("Error Retrieving Open orders.");
        return openOrders;
    }

    public static List<Order> getOpenOrders(HitBtcAPI hitBtcAPI, String symbol) {
        String uri = REQUEST_URI + "?symbol=" + symbol;
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Order> openOrders = apiAuthRequest.getListRequest(uri, Order[].class);
        if (openOrders.isEmpty())
            LOG.error("Error Retrieving Open orders.");
        return openOrders;
    }

    public static Optional<Order> sendLimitOrder(HitBtcAPI hitBtcAPI, String symbol, String side, String quantity, String price) {
        TradeType tradeType = new TradeType(TradeType.LIMIT);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);
        boolean postOnly = false;

        return OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, postOnly);
    }

    public static Optional<Order> sendMarketBuyOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendMarketOrder(hitBtcAPI, symbol, amount, Side.BUY);
    }

    public static Optional<Order> sendMarketSellOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendMarketOrder(hitBtcAPI, symbol, amount, Side.SELL);
    }

    /* There are Risks involved when using this "Like-Market" order. Some of these Risks are:
    *  1) No Guarantees that your order will be filled fast, soon, or at all from the time the Order is placed.
    *  2) If there is a current Bull/Bear Market and a Like-Market Order is placed, then the actual market can run away from your order in the opposite direction rather fast.
    *  3) Other Trades can see your order as it is posted and then bid Over/Under you almost immediately, putting you further down the line.
    *  4) You Need to expect a Minimal Wait time of up to 5 minutes to monitor where the market is moving away or close to your order.
    *  If the above Risks and Warnings are too painful for you, Consider using the Market Order instead.
    */
    public static Optional<Order> sendLikeMarketBuyOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendLikeMarketOrder(hitBtcAPI, symbol, amount, Side.BUY);
    }

    public static Optional<Order> sendLikeMarketSellOrder(HitBtcAPI hitBtcAPI, String symbol, String amount) {
        return sendLikeMarketOrder(hitBtcAPI, symbol, amount, Side.SELL);
    }

    public static Optional<Order> sendQuantityMarketBuyOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity) {
        return sendQuantityMarketOrder(hitBtcAPI, symbol, quantity, Side.BUY);
    }

    public static Optional<Order> sendQuantityMarketSellOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity) {
        return sendQuantityMarketOrder(hitBtcAPI, symbol, quantity, Side.SELL);
    }

    public static Optional<Order> sendQuantityLikeMarketBuyOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity) {
        return sendQuantityMarketOrder(hitBtcAPI, symbol, quantity, Side.BUY);
    }

    public static Optional<Order> sendQuantityLikeMarketSellOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity) {
        return sendQuantityLikeMarketOrder(hitBtcAPI, symbol, quantity, Side.SELL);
    }

    private static Optional<Order> sendMarketOrder(HitBtcAPI hitBtcAPI, String symbol, String amount, String side) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);
        String price = ticker.getLast();
        String quantity = "";

        switch (side) {
            case "buy":
                quantity = ticker.getMarketBuyQuantityByAmount(Float.parseFloat(amount));
                break;
            case "sell":
                quantity = ticker.getMarketSellQuantityByAmount(Float.parseFloat(amount));
                break;
        }

        TradeType tradeType = new TradeType(TradeType.MARKET);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.IOC_IMMEDIATE_OR_CANCEL);

        Optional<Order> responseOrder = OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, false);
        responseOrder.ifPresent(Order::reconcileMarketOrder);
        return responseOrder;
    }

    private static Optional<Order> sendLikeMarketOrder(HitBtcAPI hitBtcAPI, String symbol, String amount, String side) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);
        String price = "";

        switch (side) {
            case "buy":
                price = ticker.getBidPlus();
                break;
            case "sell":
                price = ticker.getAskMinus();
                break;
        }

        String quantity = ticker.getMarketQuantityByAmount(Float.parseFloat(amount), Float.parseFloat(price));

        TradeType tradeType = new TradeType(TradeType.LIMIT);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);

        return OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, true);
    }

    private static Optional<Order> sendQuantityMarketOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity, String side) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);
        String price = ticker.getLast();

        TradeType tradeType = new TradeType(TradeType.MARKET);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.IOC_IMMEDIATE_OR_CANCEL);

        Optional<Order> responseOrder = OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, false);

        responseOrder.ifPresent(Order::reconcileMarketOrder);

        return responseOrder;
    }

    private static Optional<Order> sendQuantityLikeMarketOrder(HitBtcAPI hitBtcAPI, String symbol, String quantity, String side) {
        Ticker ticker = TickerRestClient.getTickerById(symbol);
        String price = "";

        switch (side) {
            case "buy":
                price = ticker.getBidPlus();
                break;
            case "sell":
                price = ticker.getAskMinus();
                break;
        }

        TradeType tradeType = new TradeType(TradeType.LIMIT);
        TimeInForce timeInForce = new TimeInForce(TimeInForce.GTC_GOOD_TILL_CANCELLED);

        return OrderRestClient.sendOrder(hitBtcAPI, symbol, side, quantity, price, tradeType, timeInForce, true);
    }

    private static Optional<Order> sendOrder(HitBtcAPI hitBtcAPI, String symbol, String side, String quantity, String price,
                                             TradeType tradeType, TimeInForce timeInForce, boolean postOnly) {
        ApiAuthRequest<Order> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("symbol", symbol);
        map.add("side", side);
        map.add("type", tradeType.toString());
        map.add("timeInForce", timeInForce.toString());
        map.add("quantity", quantity);
        map.add("price", price);
        if (postOnly)
            map.add("postOnly", "true");
        else map.add("postOnly", "false");

        LOG.info(String.format("Submitted %s %s Order: Price=%s, Quantity=%s, Symbol=%s, TimeInForce=%s",
                tradeType.toString(), side, price, quantity, symbol, timeInForce.toString()));

        Optional<Order> submittedOrder = apiAuthRequest.postRequest(REQUEST_URI, map, Order.class);
        if (submittedOrder.isEmpty())
            LOG.error("Submitting Order Failed.");
        return submittedOrder;
    }
}
