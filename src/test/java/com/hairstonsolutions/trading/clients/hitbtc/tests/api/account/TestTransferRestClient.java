package com.hairstonsolutions.trading.clients.hitbtc.tests.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Direction;
import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.account.TransferRestClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestTransferRestClient {

    final String TESTCONFIGFILE = "src/test/resources/hitbtckey.properties";
    private HitBtcAPI hitBtcAPI;

    @Before
    public void load() {
        hitBtcAPI = new HitBtcAPI();
        hitBtcAPI.loadKeysFromPropertiesFile(TESTCONFIGFILE);
    }

    @Ignore
    @Test
    public void moveBTCtoMain() {
        String currency = "BTC";
        String direction = Direction.TO_MAIN_BANK;
        String amount = "0.0003";

        TransferRestClient transferRestClient = new TransferRestClient(hitBtcAPI);

        ResponseEntity<TransferResponse> responseEntity = transferRestClient.transferExecution(currency, direction, amount);

        assert responseEntity.getStatusCode().toString().equals("200 OK");
        System.out.println(responseEntity.getStatusCode().toString());

        TransferResponse transferResponse = responseEntity.getBody();

        System.out.println(transferResponse.toString());
    }

    @Ignore
    @Test
    public void moveBTCtoTrading() {
        String currency = "BTC";
        String direction = Direction.TO_TRADING;
        String amount = "0.0003";

        TransferRestClient transferRestClient = new TransferRestClient(hitBtcAPI);

        ResponseEntity<TransferResponse> responseEntity = transferRestClient.transferExecution(currency, direction, amount);

        assert responseEntity.getStatusCode().toString().equals("200 OK");
        System.out.println(responseEntity.getStatusCode().toString());

        TransferResponse transferResponse = responseEntity.getBody();

        System.out.println(transferResponse.toString());
    }

    @Ignore
    @Test
    public void simpleMoveBTCtoTrading() {
        String currency = "BTC";
        String amount = "0.0009";

        TransferRestClient.moveToTrading(hitBtcAPI,currency,amount);
    }

    @Ignore
    @Test
    public void simpleMoveBTCtoMain() {
        String currency = "BTC";
        String amount = "0.0009";

        TransferRestClient.moveToMain(hitBtcAPI,currency,amount);
    }
}
