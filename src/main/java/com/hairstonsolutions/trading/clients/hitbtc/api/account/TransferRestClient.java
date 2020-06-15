package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Direction;
import com.hairstonsolutions.trading.clients.hitbtc.account.TransferResponse;
import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public class TransferRestClient {

    private static final String RESOURCE_PATH = "/account/transfer";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(TransferRestClient.class);

    public static Optional<TransferResponse> moveToTrading(HitBtcAPI hitBtcAPI, String currency, String amount) {
        String direction = Direction.TO_TRADING;
        return transfer(hitBtcAPI, currency, amount, direction);
    }

    public static Optional<TransferResponse> moveToMain(HitBtcAPI hitBtcAPI, String currency, String amount) {
        String direction = Direction.TO_MAIN_BANK;
        return transfer(hitBtcAPI, currency, amount, direction);
    }

    private static Optional<TransferResponse> transfer(HitBtcAPI hitBtcAPI, String currency, String amount, String direction) {
        ApiAuthRequest<TransferResponse> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("currency", currency);
        map.add("type", direction);
        map.add("amount", amount);

        Optional<TransferResponse> transferResponse = apiAuthRequest.postRequest(REQUEST_URI, map, TransferResponse.class);

        transferResponse.ifPresent(response -> LOG.info(String.format("Transfer Response: %s", response)));

        return transferResponse;
    }
}
