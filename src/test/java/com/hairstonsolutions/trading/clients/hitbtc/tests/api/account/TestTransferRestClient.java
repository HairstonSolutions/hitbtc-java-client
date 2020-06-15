package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TransferRestClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

public class TestTransferRestClient {

    final String TEST_CONFIG_FILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TEST_CONFIG_FILE);
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
