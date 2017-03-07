package com.ebways.cat;

/**
 * Created by kevin on 2017/3/7.
 */
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

public class TestCat {

    public static void main(String args[]) {
        TestCat cat = new TestCat();
        cat.testCat();

    }

    public void testCat(){
        String pageName = "Ebways";
        String serverIp = "192.168.1.1";
        double amount = 10;

        Transaction t = Cat.newTransaction("URL", pageName);
        try {
            Cat.logEvent("URL.Server", serverIp, Event.SUCCESS, "ip=" + serverIp + "&...");
            Cat.logMetricForCount("PayCount");
            Cat.logMetricForSum("PayAmount", amount);

            Thread.sleep(11000);

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            Cat.logError(e);
            t.setStatus(e);
        } finally {
            t.complete();
        }
    }
}
