package com.hairstonsolutions.trading.clients.hitbtc.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ApiAuthRequest<T> {
    private static final Log LOG = LogFactory.getLog(ApiAuthRequest.class);

    private HitBtcAPI hitBtcAPI;
    private HttpHeaders httpHeaders;

    public ApiAuthRequest(HitBtcAPI hitBtcAPI) {
        this.hitBtcAPI = hitBtcAPI;
        this.httpHeaders = new HttpHeaders();
        String encodedCredentials = hitBtcAPI.getEncodedCredentials();
        httpHeaders.set("Authorization", "Basic " + encodedCredentials);
    }

    public Optional<T> getItemRequest(String requestURI, Class<T[]> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<T[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange(requestURI, HttpMethod.GET, httpEntity, responseType);
        }
        catch (Exception e) {
            LOG.error(String.format("Error Retrieving request from API. API Response: %s", e.getMessage()));
            return Optional.empty();
        }

        LOG.debug(String.format("Return Values: %s", responseEntity.toString()));

        if (responseEntity.hasBody()) {
            List<T> items = Arrays.asList(responseEntity.getBody());
            if (items.isEmpty())
                return Optional.empty();
            else
                return Optional.of(items.get(0));
        }
        else
            return Optional.empty();
    }

    public List<T> getListRequest(String requestURI, Class<T[]> responseType) {
        List<T> items = new LinkedList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<T[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange(requestURI, HttpMethod.GET, httpEntity, responseType);
        }
        catch (Exception e) {
            LOG.error(String.format("Error Retrieving List request from API. API Response: %s", e.getMessage()));
            return items;
        }

        LOG.debug(String.format("Return Values: %s", responseEntity.toString()));

        if (responseEntity.hasBody()) {
            items = Arrays.asList(responseEntity.getBody());
        }
        return items;
    }

    public Optional<T> postRequest(String requestURI, MultiValueMap<String, String> map, Class<T> responseType){
        RestTemplate restTemplate = new RestTemplate();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<T> responseEntity;

        try {
            responseEntity = restTemplate.exchange(requestURI, HttpMethod.POST, httpEntity, responseType);
        }
        catch (Exception e) {
            LOG.error(String.format("Error Posting request to API. API Response: %s", e.getMessage()));
            return Optional.empty();
        }

        LOG.debug(String.format("Return Values: %s", responseEntity.toString()));

        if (responseEntity.hasBody())
            return Optional.of(responseEntity.getBody());
        else
            return Optional.empty();
    }
}
