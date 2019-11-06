package com.hairstonsolutions.trading.clients.hitbtc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Symbol {

    private String id;
    private String baseCurrency;
    private String quoteCurrency;
    private String quantityIncrement;
    private String tickSize;
    private String takeLiquidityRate;
    private String provideLiquidityRate;
    private String feeCurrency;

    public String getId() {
        return id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public String getQuantityIncrement() {
        return quantityIncrement;
    }

    public String getTickSize() {
        return tickSize;
    }

    public String getTakeLiquidityRate() {
        return takeLiquidityRate;
    }

    public String getProvideLiquidityRate() {
        return provideLiquidityRate;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }
}

/*
{
  "id": "BTCUSDT20",
  "baseCurrency": "BTC",
  "quoteCurrency": "USDT20",
  "quantityIncrement": "0.00001",
  "tickSize": "0.01",
  "takeLiquidityRate": "0.002",
  "provideLiquidityRate": "0.001",
  "feeCurrency": "USDT20"
}
 */