package com.hairstonsolutions.trading.clients.hitbtc.tests.attributes;

import com.hairstonsolutions.trading.clients.hitbtc.attributes.TimeInForce;
import org.junit.Test;

public class TestTimeInForce {

    @Test
    public void testDefault() {
        String myTimeInForce = TimeInForce.selectTimeInForce(TimeInForce.DEFAULT);
        assert (myTimeInForce.equals("GTC"));
    }

    @Test
    public void testGoodTillCancelled() {
        String myTimeInForce = TimeInForce.selectTimeInForce("GTC");
        assert (myTimeInForce.equals(TimeInForce.GTC_GOOD_TILL_CANCELLED));
    }

    @Test
    public void testImmediateOrCancel() {
        String myTimeInForce = TimeInForce.selectTimeInForce("IOC");
        assert (myTimeInForce.equals(TimeInForce.IOC_IMMEDIATE_OR_CANCEL));
    }

    @Test
    public void testFillOrKill() {
        String myTimeInForce = TimeInForce.selectTimeInForce("FOK");
        assert (myTimeInForce.equals(TimeInForce.FOK_FILL_OR_KILL));
    }

    @Test
    public void testDay() {
        String myTimeInForce = TimeInForce.selectTimeInForce("Day");
        assert (myTimeInForce.equals(TimeInForce.DAY));
    }

    @Test
    public void testGoodTillDatetime() {
        String myTimeInForce = TimeInForce.selectTimeInForce("GTD");
        assert (myTimeInForce.equals(TimeInForce.GTD_GOOD_TILL_DATETIME));
    }

    @Test
    public void testEmptyInput() {
        String myTimeInForce = TimeInForce.selectTimeInForce("");
        assert (myTimeInForce.equals(TimeInForce.DEFAULT));
    }

    @Test
    public void testWrongInput() {
        String myTimeInForce = TimeInForce.selectTimeInForce("fdafdsa");
        assert (myTimeInForce.equals(TimeInForce.DEFAULT));
    }
}
