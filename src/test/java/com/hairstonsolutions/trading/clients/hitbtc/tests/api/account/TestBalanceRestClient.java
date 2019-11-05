package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.BalanceRestClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

        int count = 0;
        for ( Balance bal : balances ) {
            System.out.println(bal);
            count++;
        }

        System.out.println(String.format("Balances Retrived: %s", count));
    }
}
