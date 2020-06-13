package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Optional;

public class BalanceRestClient {

    private static final String RESOURCE_PATH = "/account/balance";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(BalanceRestClient.class);

    public static List<Balance> retrieveBalances(HitBtcAPI hitBtcAPI) {
        ApiAuthRequest<Balance> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Balance> balances = apiAuthRequest.getListRequest(REQUEST_URI,Balance[].class);

        if (balances.isEmpty())
            LOG.error("There was an Error in retrieving Balances");

        return balances;
    }

    public static Optional<Balance> getBalance(HitBtcAPI hitBtcAPI, String currency) {
        List<Balance> balances = retrieveBalances(hitBtcAPI);

        for (Balance singleBalance : balances)
            if (singleBalance.getCurrency().equals(currency))
                return Optional.of(singleBalance);

        return Optional.empty();
    }
}
