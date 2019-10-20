package com.hairstonsolutions.trading.clients.hitbtc.tests.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Symbol;
import com.hairstonsolutions.trading.clients.hitbtc.api.open.SymbolRestClient;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestSymbol {

    @Test
    public void getBTCSymbol() {
        SymbolRestClient symbolRestClient = new SymbolRestClient(new RestTemplate());

        ResponseEntity<Symbol> myGetSymbolResponseEntity = symbolRestClient.getForEntity("BTCUSD");

        assert (myGetSymbolResponseEntity.getStatusCode().toString().equals("200 OK"));

        Symbol mySymbol = myGetSymbolResponseEntity.getBody();

        assert mySymbol != null;
        assert (mySymbol.getId().equals("BTCUSD"));
        assert (mySymbol.getBaseCurrency().equals("BTC"));
        assert (mySymbol.getQuoteCurrency().equals("USD"));
        assert (mySymbol.getQuantityIncrement().equals("0.00001"));
        assert (mySymbol.getTickSize().equals("0.01"));
        assert (mySymbol.getTakeLiquidityRate().equals("0.002"));
        assert (mySymbol.getProvideLiquidityRate().equals("0.001"));
        assert (mySymbol.getFeeCurrency().equals("USD"));
    }

    @Test
    public void getDENTSymbol() {
        SymbolRestClient symbolRestClient = new SymbolRestClient(new RestTemplate());

        ResponseEntity<Symbol> myGetSymbolResponseEntity = symbolRestClient.getForEntity("DENTBTC");

        assert (myGetSymbolResponseEntity.getStatusCode().toString().equals("200 OK"));

        Symbol mySymbol = myGetSymbolResponseEntity.getBody();

        assert mySymbol != null;
        assert (mySymbol.getId().equals("DENTBTC"));
        assert (mySymbol.getBaseCurrency().equals("DENT"));
        assert (mySymbol.getQuoteCurrency().equals("BTC"));
        assert (mySymbol.getQuantityIncrement().equals("100"));
        assert (mySymbol.getTickSize().equals("0.00000000001"));
        assert (mySymbol.getTakeLiquidityRate().equals("0.002"));
        assert (mySymbol.getProvideLiquidityRate().equals("0.001"));
        assert (mySymbol.getFeeCurrency().equals("BTC"));
    }
}
