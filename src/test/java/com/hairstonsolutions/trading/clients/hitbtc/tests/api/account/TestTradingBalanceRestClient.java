package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TradingBalanceRestClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class TestTradingBalanceRestClient {

    final String TEST_CONFIG_FILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TEST_CONFIG_FILE);
    }

    @Test
    public void getAllBalances() {
        List<Balance> balances = TradingBalanceRestClient.getBalances(hitBtcAPI);

        System.out.println(balances);
        System.out.println(String.format("Balances Retrieved: %s", balances.size()));
    }

    @Test
    public void getOneBalance() {
        String currency = "BTC";
        Optional<Balance> balance = TradingBalanceRestClient.getBalance(hitBtcAPI, currency);
        balance.ifPresent(System.out::println);
        assert balance.isEmpty() || balance.get().getCurrency().equals(currency);
    }

}
