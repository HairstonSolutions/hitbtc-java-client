package com.hairstonsolutions.trading.clients.hitbtc.api.account;

import com.hairstonsolutions.trading.clients.hitbtc.account.Balance;
import com.hairstonsolutions.trading.clients.hitbtc.api.ApiAuthRequest;
import com.hairstonsolutions.trading.clients.hitbtc.api.HitBtcAPI;
import com.hairstonsolutions.trading.clients.hitbtc.api.history.HistoricalOrderRestClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Optional;

public class TradingBalanceRestClient {

    private static final String RESOURCE_PATH = "/trading/balance";
    private static final String REQUEST_URI = HitBtcAPI.BASE_URL + RESOURCE_PATH;
    private static final Log LOG = LogFactory.getLog(HistoricalOrderRestClient.class);

    public static List<Balance> getBalances(HitBtcAPI hitBtcAPI) {
        ApiAuthRequest<Balance> apiAuthRequest = new ApiAuthRequest<>(hitBtcAPI);
        List<Balance> balances = apiAuthRequest.getListRequest(REQUEST_URI, Balance[].class);

        if (balances.isEmpty())
            LOG.error("There was an Error in retrieving Balances");

        return balances;
    }

    public static Optional<Balance> getBalance(HitBtcAPI hitBtcAPI, String currency) {
        List<Balance> balances = getBalances(hitBtcAPI);

        for (Balance singleBalance : balances)
            if (singleBalance.getCurrency().equals(currency))
                return Optional.of(singleBalance);

        return Optional.empty();
    }
}
