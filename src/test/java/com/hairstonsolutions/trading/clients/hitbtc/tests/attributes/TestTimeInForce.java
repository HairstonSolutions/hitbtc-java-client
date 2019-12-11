package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import org.junit.Test;

public class TestTimeInForce {

    @Test
    public void testDefault() {
        TimeInForce mytimeInForce = new TimeInForce();
        assert(mytimeInForce.getForce().equals("GTC"));
        assert(mytimeInForce.getForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testGoodTillCancelled() {
        TimeInForce mytimeInForce = new TimeInForce("GTC");
        assert(mytimeInForce.getForce().equals("GTC"));
        assert(mytimeInForce.getForce().equals(TimeInForce.GTC_GOOD_TILL_CANCELLED));
    }

    @Test
    public void testImmediateOrCancel() {
        TimeInForce myTimeInForce = new TimeInForce("IOC");
        assert(myTimeInForce.getForce().equals("IOC"));
        assert (myTimeInForce.getForce().equals(TimeInForce.IOC_IMMEDIATE_OR_CANCEL));
    }

    @Test
    public void testFillOrKill() {
        TimeInForce myTimeInForce = new TimeInForce("FOK");
        assert (myTimeInForce.getForce().equals("FOK"));
        assert (myTimeInForce.getForce().equals(TimeInForce.FOK_FILL_OR_KILL));
    }

    @Test
    public void testDay() {
        TimeInForce myTimeInForce = new TimeInForce("Day");
        assert (myTimeInForce.getForce().equals("Day"));
        assert (myTimeInForce.getForce().equals(TimeInForce.DAY));
    }

    @Test
    public void testGoodTillDatetime() {
        TimeInForce myTimeInForce = new TimeInForce("GTD");
        assert(myTimeInForce.getForce().equals("GTD"));
        assert(myTimeInForce.getForce().equals(TimeInForce.GTD_GOOD_TILL_DATETIME));
    }

    @Test
    public void testEmptyInput() {
        TimeInForce timeInForce = new TimeInForce("");
        assert (timeInForce.getForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        TimeInForce timeInForce = new TimeInForce("fdafdsa");
        assert (timeInForce.getForce().equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testObjectPrintout() {
        TimeInForce timeInForce = new TimeInForce();
        assert (timeInForce.toString().equals(TimeInForce.DEFAULT));
    }
}
