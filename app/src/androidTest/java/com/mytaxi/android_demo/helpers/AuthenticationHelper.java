package com.mytaxi.android_demo.helpers;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AuthenticationHelper {
    public static void perform_login(String user, String pass) {
        onView(withId(R.id.edt_username)).perform(typeText(user), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(typeText(pass), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Utilities.waitForSeconds(3000);
        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));

    }
}

