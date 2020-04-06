package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import org.junit.Test;

public class TestTimeInForce {

    @Test
    public void testDefault() {
        TimeInForce mytimeInForce = new TimeInForce();
        assert (mytimeInForce.getTimeInForce().equals("GTC"));
        assert (mytimeInForce.getTimeInForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testGoodTillCancelled() {
        TimeInForce mytimeInForce = new TimeInForce("GTC");
        assert (mytimeInForce.getTimeInForce().equals("GTC"));
        assert (mytimeInForce.getTimeInForce().equals(TimeInForce.GTC_GOOD_TILL_CANCELLED));
    }

    @Test
    public void testImmediateOrCancel() {
        TimeInForce myTimeInForce = new TimeInForce("IOC");
        assert (myTimeInForce.getTimeInForce().equals("IOC"));
        assert (myTimeInForce.getTimeInForce().equals(TimeInForce.IOC_IMMEDIATE_OR_CANCEL));
    }

    @Test
    public void testFillOrKill() {
        TimeInForce myTimeInForce = new TimeInForce("FOK");
        assert (myTimeInForce.getTimeInForce().equals("FOK"));
        assert (myTimeInForce.getTimeInForce().equals(TimeInForce.FOK_FILL_OR_KILL));
    }

    @Test
    public void testDay() {
        TimeInForce myTimeInForce = new TimeInForce("Day");
        assert (myTimeInForce.getTimeInForce().equals("Day"));
        assert (myTimeInForce.getTimeInForce().equals(TimeInForce.DAY));
    }

    @Test
    public void testGoodTillDatetime() {
        TimeInForce myTimeInForce = new TimeInForce("GTD");
        assert (myTimeInForce.getTimeInForce().equals("GTD"));
        assert (myTimeInForce.getTimeInForce().equals(TimeInForce.GTD_GOOD_TILL_DATETIME));
    }

    @Test
    public void testEmptyInput() {
        TimeInForce timeInForce = new TimeInForce("");
        assert (timeInForce.getTimeInForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        TimeInForce timeInForce = new TimeInForce("fdafdsa");
        assert (timeInForce.getTimeInForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testObjectPrintout() {
        TimeInForce timeInForce = new TimeInForce();
        assert (timeInForce.toString().equals(TimeInForce.DEFAULT));
    }
}
