package com.mytaxi.android_demo.test;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.LargeTest;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.data.TestData;
import com.mytaxi.android_demo.pages.DriverPage;
import com.mytaxi.android_demo.pages.LoginPage;
import com.mytaxi.android_demo.pages.SearchPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest extends AndroidJUnitRunner {

    LoginPage loginObject;
    SearchPage searchObject;

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class,true,false);


    String PACKAGE_ANDROID_DIALER = "com.android.dialer";
    SearchPage searchPage = new SearchPage();
    DriverPage driverPage = new DriverPage();

    @Before
    public void setUp(){
        Intents.init();
        // AuthenticationActivity.createIntent(MainActivity, AuthenticationActivity.class);
        mActivityRule.launchActivity(new Intent());

       // intended(hasComponent(MainActivity.class.getName()));

        Intents.release();

      // User user =  mSharedPrefStorage.loadUser();
       // mActivityRule.launchActivity(null);
       // dActivityRule.getActivity();
        //       // .getSupportFragmentManager().beginTransaction();
            //Intent intent = new Intent();
           /* Driver driver=new Driver(TestData.DriverProfile.SarahDriver.getDriverName(),TestData.DriverProfile.SarahDriver.getDriverPhoneNum(),
                    "ddd",TestData.DriverProfile.SarahDriver.getDriverLocation(), new Date()); */
            //intent.putExtra("mainactivity",user);
            //mActivityRule.launchActivity(intent);

        //AppComponent appComponent = App.getApplicationContext(dActivityRule.getClass().).getAppComponent();
        //appComponent.inject(dActivityRule.launchActivity(intent));

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


    @Test
    public void testSearchForDriver(){
        String username, password;
        loginObject = new LoginPage();
        searchObject = new SearchPage();
        username = TestData.UserLoginData.validUser.getUserName();
        password = TestData.UserLoginData.validUser.getPassword();
        loginObject.login(username,password);
        searchObject.getTextSearch().check(matches(isDisplayed()));
    }

}
