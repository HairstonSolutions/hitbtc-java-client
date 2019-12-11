package com.hairstonsolutions.trading.clients.hitbtc.attributes;

public class Status {
    public static final String NEW = "new";
    public static final String SUSPENDED = "suspended";
    public static final String PARTIALLY_FILLED = "partiallyFilled";
    public static final String FILLED = "filled";
    public static final String CANCELED = "canceled";
    public static final String EXPIRED = "expired";
    public static final String DEFAULT = NEW;

    private String status;

    public Status() {
        this.status = DEFAULT;
    }

    public Status(String status) {
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        switch (status) {
            case "new":
                this.status = NEW;
                break;
            case "suspended":
                this.status = SUSPENDED;
                break;
            case "partiallyFilled":
                this.status = PARTIALLY_FILLED;
                break;
            case "filled":
                this.status = FILLED;
                break;
            case "canceled":
                this.status = CANCELED;
                break;
            case "expired":
                this.status = EXPIRED;
                break;
            default:
                this.status = DEFAULT;
        }
    }

    @Override
    public String toString() {
        return status;
    }
}