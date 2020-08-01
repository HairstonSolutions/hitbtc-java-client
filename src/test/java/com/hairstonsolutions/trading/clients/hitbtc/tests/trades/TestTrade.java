package com.hairstonsolutions.trading.clients.hitbtc.tests.trades;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import com.hairstonsolutions.trading.clients.hitbtc.trades.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class TestTrade {
    private HitBtcAPI hitBtcAPI;
    private AnnotationConfigApplicationContext context;
    private ConfigurableEnvironment env;

    @Before
    public void load() {
        context = new AnnotationConfigApplicationContext();
        env = context.getEnvironment();

        String apiKey = env.getProperty("TEST_HITBTC_API_KEY");
        String secretKey = env.getProperty("TEST_HITBTC_API_SECRET");
        hitBtcAPI = new HitBtcAPI(apiKey, secretKey);
    }

    @Test
    public void calculateTotalCostGross() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);

            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has a Total Cost(Gross): %s\n", trade.getId(), trade.getTotalCostGross());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));
            assert tradeReport.size() > 0;
        }
    }

    @Test
    public void calculateTotalCostNet() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);

            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has a Total Net (Gross): %s\n", trade.getId(), trade.getTotalCostNet());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));
            assert tradeReport.size() > 0;
        }
    }

    @Test
    public void calculateListTotalCostGross() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);

            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has a Total Cost (Gross): %s\n", trade.getId(), trade.getTotalCostGross());
            }

            String GrossTotalCost = Trade.getTotalCostGross(tradeReport);
            System.out.printf("Trade List Total Cost (Gross): %s\n", GrossTotalCost);
            assert GrossTotalCost.equals("21.0283668");
        }
    }

    @Test
    public void calculateListTotalCostNet() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);

            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has a Total Cost (Net): %s\n", trade.getId(), trade.getTotalCostNet());
            }

            System.out.printf("Trade List Total Cost (Gross): %s\n", Trade.getTotalCostGross(tradeReport));
            System.out.printf("Trade List Total Fees        :  %s\n", Trade.getTotalFee((tradeReport)));

            String NetTotalCost = Trade.getTotalCostNet(tradeReport);
            System.out.printf("Trade List Total Cost (Net)  : %s\n", NetTotalCost);
            assert NetTotalCost.equals("21.0704235336");
        }
    }

    @Test
    public void calculateListCumulativeQuantity() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);
            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has quantity: %s\n", trade.getId(), trade.getQuantity());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

            String cumQuantity = Trade.getCumulativeQuantity(tradeReport);
            System.out.printf("Cumulative Quantity: %s\n", cumQuantity);
            assert cumQuantity.equals("0.00238");
        }
    }

    @Test
    public void calculateAveragePrice() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);
            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has price: %s\n", trade.getId(), trade.getPrice());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

            String averagePrice = Trade.getAveragePrice(tradeReport);
            System.out.printf("Average Price: %s\n", averagePrice);
            assert averagePrice.equals("8835.45");
        }
    }

    @Test
    public void calculatePreciseAveragePrice() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);
            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has price: %s\n", trade.getId(), trade.getPrice());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

            String averagePrice = Trade.getPreciseAveragePrice(tradeReport);
            System.out.printf("Precise Average Price: %s\n", averagePrice);
            assert averagePrice.equals("8835.45");
        }
    }

    @Test
    public void calculateTotalFee() {
        long orderId = 176617860956L;
        List<Trade> tradeReport = HistoricalOrderRestClient.getTradesByOrderId(hitBtcAPI, orderId);

        if (!tradeReport.isEmpty()) {
            System.out.println(tradeReport);

            for (Trade trade : tradeReport) {
                System.out.printf("Trade ID %s has Fee: %s\n", trade.getId(), trade.getFee());
            }
            System.out.println(String.format("Order Trades Total: %s", tradeReport.size()));

            String totalFees = Trade.getTotalFee(tradeReport);
            System.out.printf("Total Fees: %s\n", totalFees);
            assert totalFees.equals("0.0420567336");
        }
    }
}
