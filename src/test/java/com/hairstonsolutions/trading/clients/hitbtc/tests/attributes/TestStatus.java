package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Status;
import org.junit.Test;

public class TestStatus {

    @Test
    public void testDefault() {
        Status myStatus = new Status();
        assert myStatus.getStatus().equals(Status.DEFAULT);
    }

    @Test
    public void testEmptyInput() {
        Status myStatus = new Status("");
        assert (myStatus.getStatus().equals(Status.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        Status myStatus = new Status("fdafdsa");

        assert (myStatus.getStatus().equals(Status.DEFAULT));
    }

    @Test
    public void testObjectPrintout() {
        Status myStatus = new Status();
        assert (myStatus.getStatus().equals(Status.DEFAULT));
    }
}
