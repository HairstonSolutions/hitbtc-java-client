package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.BalanceRestClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TestBalanceRestClient {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Test
    public void testAuth() {
        BalanceRestClient balanceRestClient = new BalanceRestClient(hitBtcAPI);

        ResponseEntity<Balance[]> myGetBalanceResponseEntity = balanceRestClient.getForEntity();

        assert myGetBalanceResponseEntity.getStatusCode().toString().equals("200 OK");

        Balance[] balances = myGetBalanceResponseEntity.getBody();

        assert balances != null;

        for (Balance bal : balances) {
            System.out.println(bal);
        }

        System.out.println(String.format("Balances Retrived: %s", balances.length));
    }

    @Test
    public void getAllBalances() {
        BalanceRestClient balanceRestClient = new BalanceRestClient(hitBtcAPI);
        List<Balance> balances = balanceRestClient.getBalances();

        for (Balance bal : balances) {
            System.out.println(bal);
        }

        System.out.println(String.format("Balances Retrived: %s", balances.size()));
    }

    @Test
    public void getAllBalancesStatic() {
        List<Balance> balances = BalanceRestClient.retrieveBalances(hitBtcAPI);

        for (Balance bal : balances) {
            System.out.println(bal);
        }

        System.out.println(String.format("Balances Retrived: %s", balances.size()));
    }

    @Test
    public void getOneBalance() {
        String currency = "BTC";
        Balance balance = BalanceRestClient.getBalance(hitBtcAPI, currency);
        System.out.println(balance);
        assert balance.getCurrency().equals(currency);
    }

    @Test
    public void getOneBalance2() {
        String currency = "USDT";
        Balance balance = BalanceRestClient.getBalance(hitBtcAPI, currency);
        System.out.println(balance);
        assert balance.getCurrency().equals("USD");
    }
}
