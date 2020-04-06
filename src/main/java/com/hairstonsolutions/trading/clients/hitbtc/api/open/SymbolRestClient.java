package com.hairstonsolutions.trading.clients.hitbtc.api.open;

import com.hairstonsolutions.trading.clients.hitbtc.Symbol;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SymbolRestClient {

    private static final String RESOURCE_PATH = "/public/symbol";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(SymbolRestClient.class);

    public SymbolRestClient() {
    }

    public ResponseEntity<Symbol> getForEntity(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Symbol> responseEntity = restTemplate.getForEntity(REQUEST_URI + "/{symbol}",
                Symbol.class, symbol);

        LOG.info(String.format("Return values: %s", responseEntity.toString()));

        return responseEntity;
    }

    public Symbol getSymbol(String id) {
        return getSymbolByTickerId(id);
    }

    public static Symbol getSymbolByTickerId(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Symbol> responseEntity = restTemplate.getForEntity(REQUEST_URI + "/{id}",
                Symbol.class, id);

        LOG.info(String.format("Return values: %s", responseEntity.toString()));

        return responseEntity.getBody();
    }
}
