package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.BalanceRestClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class TestBalanceRestClient {

    final String TEST_CONFIG_FILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TEST_CONFIG_FILE);
    }

    @Test
    public void getAllBalances() {
        List<Balance> balances = BalanceRestClient.retrieveBalances(hitBtcAPI);

        System.out.println(balances);
        System.out.println(String.format("Balances Retrieved: %s", balances.size()));
    }

    @Test
    public void getOneBalance() {
        String currency = "BTC";
        Optional<Balance> balance = BalanceRestClient.getBalance(hitBtcAPI, currency);
        balance.ifPresent(System.out::println);
        assert balance.isEmpty() || balance.get().getCurrency().equals(currency);
    }

    @Test
    public void getOneBalance2() {
        String currency = "USDC";
        Optional<Balance> balance = BalanceRestClient.getBalance(hitBtcAPI, currency);
        balance.ifPresent(System.out::println);
        assert balance.isEmpty() || balance.get().getCurrency().equals(currency);
    }
}
