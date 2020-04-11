package com.hairstonsolutions.trading.clients.hitbtc.trades;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Side;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Data
public class Trade {

    private long id;
    private String clientOrderId;
    private long orderId;
    private String symbol;
    private String side;
    private String quantity;
    private String price;
    private String fee;
    private String timestamp;

    public long getId() {
        return id;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSide() {
        return side;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getFee() {
        return fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSide(String side) {
        this.side = Side.selectSide(side);
    }

    @ToString.Include
    public String getTotalCostGross() {
        BigDecimal bQuantity = new BigDecimal(quantity);
        BigDecimal bPrice = new BigDecimal(price);
        DecimalFormat df = new DecimalFormat("##.############");

        BigDecimal totalCost = bQuantity.multiply(bPrice);

        return df.format(totalCost);
    }

    @ToString.Include
    public String getTotalCostNet() {
        BigDecimal bQuantity = new BigDecimal(quantity);
        BigDecimal bPrice = new BigDecimal(price);
        BigDecimal bFee = new BigDecimal(fee);
        DecimalFormat df = new DecimalFormat("##.############");

        BigDecimal totalCost = bQuantity.multiply(bPrice);
        totalCost = totalCost.add(bFee);

        return df.format(totalCost);
    }

    public static String getTotalCostGross(List<Trade> tradeReport) {
        if (tradeReport.size() == 0)
            return "0.0";

        DecimalFormat df = new DecimalFormat("##.############");
        BigDecimal sum = new BigDecimal("0.0");

        for (Trade trade : tradeReport)
            sum = sum.add(new BigDecimal(trade.getTotalCostGross()));

        return df.format(sum);
    }

    public static String getTotalCostNet(List<Trade> tradeReport) {
        if (tradeReport.size() == 0)
            return "0.0";

        DecimalFormat df = new DecimalFormat("##.############");
        BigDecimal sum = new BigDecimal("0.0");

        for (Trade trade : tradeReport)
            sum = sum.add(new BigDecimal(trade.getTotalCostNet()));

        return df.format(sum);
    }

    public static String getAveragePrice(List<Trade> tradeReport) {
        if (tradeReport.size() == 0)
            return "0.0";

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        float sum = 0f;
        for (Trade trades : tradeReport) {
            sum = sum + Float.parseFloat(trades.getPrice());
        }

        return df.format(sum / tradeReport.size());
    }

    public static String getPreciseAveragePrice(List<Trade> tradeReport, String cumQuantity) {
        if (tradeReport.size() == 0)
            return "0.0";

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        float sum = 0f;
        for (Trade trade : tradeReport) {
            float quantityWeightPercentage = Float.parseFloat(trade.getQuantity()) / Float.parseFloat(cumQuantity);
            float weightedPrice = quantityWeightPercentage * Float.parseFloat(trade.getPrice());
            sum += weightedPrice;
        }

        return df.format(sum);
    }

    public static String getTotalFee(List<Trade> tradeReport) {
        if (tradeReport.size() == 0)
            return "0.0";

        DecimalFormat df = new DecimalFormat("##.##################");

        BigDecimal sum = new BigDecimal("0.0");
        for (Trade trades : tradeReport)
            sum = sum.add(new BigDecimal(trades.getFee()));

        return df.format(sum);
    }
}


/*
HISTORY TRADES: /history/trades
  {
    "id": 0,
    "clientOrderId": "string",
    "orderId": 0,
    "symbol": "string",
    "side": "sell",
    "quantity": "string",
    "fee": "string",
    "price": "string",
    "timestamp": "2019-10-05T20:18:54.426Z"
  }

  HISTORY ORDER TRADES: /history/order/{id}/trades
    {
    "id": 0,
    "clientOrderId": "string",
    "orderId": 0,
    "symbol": "string",
    "side": "sell",
    "quantity": "string",
    "fee": "string",
    "price": "string",
    "timestamp": "2019-10-05T20:17:27.687Z"
  }
 */