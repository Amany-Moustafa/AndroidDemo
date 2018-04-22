package com.mytaxi.android_demo.helpers;

import static junit.framework.Assert.assertNull;

public class Utilities {
    public static void waitForSeconds(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            assertNull(e);
            e.printStackTrace();
        }
    }

}
