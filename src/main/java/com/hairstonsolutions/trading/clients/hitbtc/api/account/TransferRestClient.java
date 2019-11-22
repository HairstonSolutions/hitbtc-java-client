package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Direction;
import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class TransferRestClient {

    private static Logger LOG = LoggerFactory.getLogger(TransferRestClient.class);
    private static final String RESOURCE_PATH = "/account/transfer";
    private static String REQUEST_URI = HitBtcAPI.BaseUrl + RESOURCE_PATH;

    private HitBtcAPI hitBtcAPI;

    public TransferRestClient(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
    }

    public ResponseEntity<TransferResponse> transferExecution(String currency, String direction, String amount) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedCredentials);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("currency", currency);
        map.add("type", direction);
        map.add("amount", amount);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<TransferResponse> responseEntity = restTemplate.exchange(REQUEST_URI, HttpMethod.POST, request, TransferResponse.class);

        LOG.info(String.format("Return Values: %s", responseEntity.toString()));

        return responseEntity;
    }

    public static void moveToTrading(HitBtcAPI hitBtcAPI, String currency, String amount) {
        String direction = Direction.TO_TRADING;

        transfer(hitBtcAPI, currency, amount, direction);
    }

    public static void moveToMain(HitBtcAPI hitBtcAPI, String currency, String amount) {
        String direction = Direction.TO_MAIN_BANK;

        transfer(hitBtcAPI, currency, amount, direction);
    }

    private static void transfer(HitBtcAPI hitBtcAPI, String currency, String amount, String direction) {
        TransferRestClient transferRestClient = new TransferRestClient(hitBtcAPI);

        ResponseEntity<TransferResponse> responseEntity = transferRestClient.transferExecution(currency, direction, amount);

        LOG.info(responseEntity.getStatusCode().toString());

        TransferResponse transferResponse = responseEntity.getBody();
        LOG.info(transferResponse.toString());
    }

}
