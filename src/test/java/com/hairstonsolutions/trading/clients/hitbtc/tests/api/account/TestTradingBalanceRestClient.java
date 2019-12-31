package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TradingBalanceRestClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestTradingBalanceRestClient {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void getAllBalances() {
        List<Balance> balances = TradingBalanceRestClient.getBalances(hitBtcAPI);

        for ( Balance bal : balances ) {
            System.out.println(bal);
        }

        System.out.println(String.format("Balances Retrived: %s", balances.size()));
    }

    @Test
    public void getOneBalance() {
        String currency = "BTC";
        Balance balance = TradingBalanceRestClient.getBalance(hitBtcAPI, currency);
        System.out.println(balance);
        assert balance.getCurrency().equals(currency);
    }

}
