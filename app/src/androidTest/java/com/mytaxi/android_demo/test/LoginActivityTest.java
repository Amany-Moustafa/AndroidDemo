package com.mytaxi.android_demo.test;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.data.TestData;
import com.mytaxi.android_demo.pages.LoginPage;
import com.mytaxi.android_demo.pages.SearchPage;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginActivityTest {
    LoginPage loginObject;
    SearchPage searchObject;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        // Before Testcase execution
    }

    @Test
    public void login_with_valid_user(){
        String username, password;
        loginObject = new LoginPage();
        searchObject = new SearchPage();
        username = TestData.UserLoginData.validUser.getUserName();
        password = TestData.UserLoginData.validUser.getPassword();
        loginObject.login(username,password);
        searchObject.getTextSearch().check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        //After Testcase execution
    }
}





