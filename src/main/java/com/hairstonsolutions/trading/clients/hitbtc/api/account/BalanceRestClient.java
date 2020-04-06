package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BalanceRestClient {

    private static final String RESOURCE_PATH = "/account/balance";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(BalanceRestClient.class);

    private HitBtcAPI hitBtcAPI;

    public BalanceRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public ResponseEntity<Balance[]> getForEntity() {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Balance[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Balance[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity;
    }

    public List<Balance> getBalances() {
        return retrieveBalances(hitBtcAPI);
    }

    public static List<Balance> retrieveBalances(HitBtcAPI hitBtcAPI) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Balance[]> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.GET, httpEntity, Balance[].class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static Balance getBalance(HitBtcAPI hitBtcAPI, String currency) {
        List<Balance> balances = retrieveBalances(hitBtcAPI);
        Balance selectedBalance = new Balance("USD", "0.0", "0.0");

        for (Balance bal : balances) {
            if (bal.getCurrency().equals(currency))
                selectedBalance = bal;
        }
        return selectedBalance;
    }
}
