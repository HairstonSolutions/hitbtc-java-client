package com.hairstonsolutions.trading.clients.hitbtc.tests.api;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestHitBtcAPI {

    String configFile = "src/main/resources/hitbtckey.properties";
    String testConfigFile = "src/test/resources/hitbtckey.properties";

    String apiKey = "";
    String secretKey = "";

    @Before
    public void LoadKeys() {

        try (InputStream input = new FileInputStream(testConfigFile)) {

            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("hitbtc.api.key");
            secretKey = prop.getProperty("hitbtc.api.secret");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("ApiKey: " + apiKey);
        System.out.println("SecretKey: " + secretKey);
    }

    @Test
    public void makeAPIRequest() {
        HitBtcAPI myhbc = new HitBtcAPI(apiKey, secretKey);
        System.out.println("Base Url: " + HitBtcAPI.BASE_URL);
        System.out.println("Keys: " + myhbc);
    }

    @Test
    public void testLoadKeysFromPropertiesFile() {
        HitBtcAPI myhbc = new HitBtcAPI(configFile);
        System.out.println("Base Url: " + HitBtcAPI.BASE_URL);
        System.out.println("Keys: " + myhbc);
        assert (myhbc.getApiKey().equals("CHANGE-ME"));
        assert (myhbc.getApiSecret().equals("CHANGE-ME"));
    }
}
