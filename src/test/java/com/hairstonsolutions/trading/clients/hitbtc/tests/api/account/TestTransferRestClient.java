package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TransferRestClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Optional;

public class TestTransferRestClient {
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

    @Ignore
    @Test
    public void moveBTCtoMain() {
        String currency = "BTC";
        String amount = "0.0003";

        Optional<TransferResponse> transferBTC = TransferRestClient.moveToMain(hitBtcAPI, currency, amount);
        transferBTC.ifPresent(System.out::println);
        assert transferBTC.isPresent();
    }

    @Ignore
    @Test
    public void moveBTCtoTrading() {
        String currency = "BTC";
        String amount = "0.0003";

        Optional<TransferResponse> transferBTC = TransferRestClient.moveToTrading(hitBtcAPI, currency, amount);
        transferBTC.ifPresent(System.out::println);
        assert transferBTC.isPresent();
    }
}
