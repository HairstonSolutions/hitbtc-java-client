package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.BalanceRestClient;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestBalanceRestClient {

    @Test
    public void testAuth() {
        String testConfigFile = "src/test/resources/hitbtckey.properties";
        HitBtcAPI hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(testConfigFile);

        //BalanceRestClient balanceRestClient = new BalanceRestClient(new RestTemplate(), hitBtcAPI);
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
