package com.mytaxi.android_demo.test;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.LargeTest;

import com.mytaxi.android_demo.App;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.data.TestData;
import com.mytaxi.android_demo.pages.DriverPage;
import com.mytaxi.android_demo.pages.LoginPage;
import com.mytaxi.android_demo.pages.SearchPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(MockitoJUnitRunner.class)
@LargeTest
public class SearchActivityTest extends MockTestRunner {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class,true,false);


    String PACKAGE_ANDROID_DIALER = "com.android.dialer";
    SearchPage searchPage = new SearchPage();
    DriverPage driverPage = new DriverPage();

    @Before
    public void setUp(){

        Context targetContext = InstrumentationRegistry.getInstrumentation()
            .getTargetContext();
        TestsApp appComponent = new TestsApp();
        appComponent.initializeAppComponent().inject(mActivityRule.getActivity());
        Intent intent = new Intent(targetContext, MainActivity.class);
        //intent.putExtra("Name", "Value");

        mActivityRule.launchActivity(intent);
        // Intents.init();
        // AuthenticationActivity.createIntent(MainActivity, AuthenticationActivity.class);
        // mActivityRule.launchActivity(new Intent());

       // intended(hasComponent(MainActivity.class.getName()));

        // Intents.release();

       // TestsApp appComponent = App.getApplicationContext().getAppComponent();
       // appComponent.initializeAppComponent();

        // to get the intent that started my activity
      //  Bundle bundle = getIntent().getExtras();

    }

    @Test
    public void testDriverProfileData(){
        searchPage.SearchForExistingDriver(TestData.SearchData.SearchCriteria.getSearchData(),TestData.SearchData.SearchCriteria.getFullName());
        driverPage.getDriverName().check(matches(withText(TestData.DriverProfile.SarahDriver.getDriverName())));
        driverPage.getDriverLocation().check(matches(withText(TestData.DriverProfile.SarahDriver.getDriverLocation())));
        driverPage.getDriverDate().check(matches(withText(TestData.DriverProfile.SarahDriver.getDriverDate())));
        driverPage.getDriverPhoto().check(matches(isDisplayed()));
    }

    @Test
    public void testToCallDriver(){
        searchPage.SearchForExistingDriver(TestData.SearchData.SearchCriteria.getSearchData(),TestData.SearchData.SearchCriteria.getFullName());
        driverPage.callDriver();
        intended(allOf(
                hasAction(Intent.ACTION_DIAL),
                hasData(TestData.DriverProfile.SarahDriver.getDriverPhoneNum()),
                toPackage(PACKAGE_ANDROID_DIALER)));
    }

}
