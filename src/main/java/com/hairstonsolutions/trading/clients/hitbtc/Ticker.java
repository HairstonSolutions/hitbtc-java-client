package com.hairstonsolutions.trading.clients.hitbtc;

import lombok.Data;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Data
public class Ticker {
    private String symbol;
    private String ask;
    private String bid;
    private String last;
    private String low;
    private String high;
    private String open;
    private String volume;
    private String volumeQuote                                                                                                     ;
    private String timestamp;


    public Ticker() {
    }

    public Ticker(String symbol, String ask, String bid, String last, String low, String high, String open, String volume, String volumeQuote, String timestamp) {
        this.symbol = symbol;
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.low = low;
        this.high = high;
        this.open = open;
        this.volume = volume;
        this.volumeQuote = volumeQuote;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getAsk() {
        return ask;
    }

    public String getBid() {
        return bid;
    }

    public String getLast() {
        return last;
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }

    public String getOpen() {
        return open;
    }

    public String getVolume() {
        return volume;
    }

    public String getVolumeQuote() {
        return volumeQuote;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setVolumeQuote(String volumeQuote) {
        this.volumeQuote = volumeQuote;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toJSON() {
        return "{ " +
                "\"symbol\": \"" + symbol + "\", " +
                "\"ask\": \"" + ask + "\", " +
                "\"bid\": \"" + bid + "\", " +
                "\"last\": \"" + last + "\", " +
                "\"low\": \"" + low + "\", " +
                "\"high\": \"" + high + "\", " +
                "\"open\": \"" + open + "\", " +
                "\"volume\": \"" + volume + "\", " +
                "\"volumeQuote\": \"" + volumeQuote + "\", " +
                "\"timestamp\": \"" + timestamp + "\"" +
                " }";
    }

    public String getMarketBuyQuantityByAmount(float amount) {
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(amount / Float.valueOf(getAsk()));
    }

    public String getMarketSellQuantityByAmount(float amount) {
        DecimalFormat df = new DecimalFormat("##.#####");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(amount / Float.valueOf(getBid()));
    }

    public String getPotentialProfit(String quantity) {
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.UP);

        float fQuantity = Float.valueOf(quantity);
        float fAsk = Float.valueOf(bid);

        return df.format(fQuantity * fAsk);
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
