package com.hairstonsolutions.trading.clients.hitbtc.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

public class HitBtcAPI {
    public static final String BaseUrl = "https://api.hitbtc.com/api/2";

    private String apiKey;
    private String apiSecret;

    public HitBtcAPI() {
    }

    public HitBtcAPI(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public HitBtcAPI(String configFile) {
        loadKeysFromPropertiesFile(configFile);
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getApiAuth() {
        return apiKey+":"+apiSecret;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    @Override
    public String toString() {
        return apiKey+":"+apiSecret;
    }

    public void loadKeysFromPropertiesFile(String configFile) {
        try (InputStream input = new FileInputStream(configFile)) {

            Properties prop = new Properties();
            prop.load(input);

            this.apiKey = prop.getProperty("hitbtc.api.key");
            this.apiSecret = prop.getProperty("hitbtc.api.secret");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getEncodedCredentials() {
        return Base64.getEncoder().encodeToString(getApiAuth().getBytes());
    }

}
