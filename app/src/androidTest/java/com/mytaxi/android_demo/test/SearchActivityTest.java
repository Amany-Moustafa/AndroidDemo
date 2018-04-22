package com.mytaxi.android_demo.test;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.assets.TestData;
import com.mytaxi.android_demo.helpers.AuthenticationHelper;
import com.mytaxi.android_demo.helpers.Utilities;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class, false, false);


    private String PACKAGE_ANDROID_DIALER = "com.google.android.dialer";
    private String validUserName = TestData.UserLoginData.validUser.getUserName();
    private String validPassword = TestData.UserLoginData.validUser.getPassword();
    private String driverName = TestData.SearchData.SearchCriteria.getSearchData();
    private String driverFullName = TestData.SearchData.SearchCriteria.getFullName();
    private String driverLocation = TestData.DriverProfile.SarahDriver.getDriverLocation();
    private String driverDate = TestData.DriverProfile.SarahDriver.getDriverDate();
    private String driverPhoneNum = TestData.DriverProfile.SarahDriver.getDriverPhoneNum();

    @Before
    public void setUp(){
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(
                    fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }
        mActivityRule.launchActivity(null);
    }

    @Test
    public void test_validateDriverProfileData() {
        AuthenticationHelper.perform_login(validUserName, validPassword);
        searchForDriver(driverName, driverFullName);
        onView(withId(R.id.textViewDriverName))
                .check(matches(withText(driverFullName)));
        onView(withId(R.id.textViewDriverLocation)).check(matches(withText(driverLocation)));
        onView(withId(R.id.textViewDriverDate)).check(matches(withText(driverDate)));
        onView(withId(R.id.imageViewDriverAvatar)).check(matches(isDisplayed()));
    }

    @Test
    public void test_validateInitiatesDriverCall_TypeNumber() {
        AuthenticationHelper.perform_login(validUserName, validPassword);
        searchForDriver(driverName, driverFullName);
        onView(withId(R.id.fab)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(driverPhoneNum),
                toPackage(PACKAGE_ANDROID_DIALER)));
    }

    private void searchForDriver(String driverName, String fullDriverName) {
        Utilities.waitForSeconds(3000);
        onView(withId(com.mytaxi.android_demo.R.id.textSearch)).perform(typeText(driverName));
        onView(withText(fullDriverName)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
    }

}
