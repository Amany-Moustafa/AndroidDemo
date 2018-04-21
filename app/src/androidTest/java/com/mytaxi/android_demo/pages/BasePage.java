package com.mytaxi.android_demo.pages;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNull;

public class BasePage {

    void clickOnItem(int Id) {
        onView(withId(Id)).perform(click());
    }

    void enterValue(int elementId, String value) {
        onView(withId(elementId)).perform(typeText(value));
    }

    void closeKeyboard() {
        closeSoftKeyboard();
    }

    void waitForTime(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            assertNull(e);
            e.printStackTrace();
        }
    }

}

