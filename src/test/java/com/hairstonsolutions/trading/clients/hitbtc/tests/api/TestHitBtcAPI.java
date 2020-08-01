package com.hairstonsolutions.trading.clients.hitbtc.tests.api;

import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestHitBtcAPI {

    private AnnotationConfigApplicationContext context;
    private ConfigurableEnvironment env;

    String configFile = "src/main/resources/hitbtckey.properties";
    String testConfigFile = "src/test/resources/hitbtckey.properties";

    String apiKey = "";
    String secretKey = "";

    @Before
    public void LoadKeys() {
        context = new AnnotationConfigApplicationContext();
        env = context.getEnvironment();

        apiKey = env.getProperty("TEST_HITBTC_API_KEY");
        secretKey = env.getProperty("TEST_HITBTC_API_SECRET");

        System.out.println("ApiKey: " + apiKey);
        System.out.println("SecretKey: " + secretKey);
    }

    @Test
    public void makeAPIRequest() {
        HitBtcAPI myhbc = new HitBtcAPI(apiKey, secretKey);
        System.out.println("Base Url: " + HitBtcAPI.BASE_URL);
        System.out.println("Keys: " + myhbc);
    }
}
