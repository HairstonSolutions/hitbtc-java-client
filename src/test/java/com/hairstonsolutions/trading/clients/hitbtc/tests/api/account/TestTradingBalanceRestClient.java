package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TradingBalanceRestClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;
import java.util.Optional;

public class TestTradingBalanceRestClient {
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
