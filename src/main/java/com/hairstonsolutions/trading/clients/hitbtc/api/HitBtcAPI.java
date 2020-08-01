package com.hairstonsolutions.trading.clients.hitbtc.api;

import java.util.Base64;

public class HitBtcAPI {
    public static final String BASE_URL = "https://api.hitbtc.com/api/2";

    private String apiKey;
    private String apiSecret;

    public HitBtcAPI() {
    }

    public HitBtcAPI(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getApiAuth() {
        return apiKey + ":" + apiSecret;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    @Override
    public String toString() {
        return apiKey + ":" + apiSecret;
    }

    public String getEncodedCredentials() {
        return Base64.getEncoder().encodeToString(getApiAuth().getBytes());
    }
}
