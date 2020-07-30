package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.Status;
import org.junit.Test;

public class TestStatus {

    @Test
    public void testNew() {
        String myStatus = Status.selectStatus("new");
        assert (myStatus.equals(Status.NEW));
    }

    @Test
    public void testEmptyInput() {
        String myStatus = Status.selectStatus("");
        assert (myStatus.equals(Status.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        String myStatus = Status.selectStatus("fdafdsa");
        assert (myStatus.equals(Status.DEFAULT));
    }

}
