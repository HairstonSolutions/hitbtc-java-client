package com.hairstonsolutions.trading.clients.hitbtc.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    private String currency;
    private String available;
    private String reserved;
}
