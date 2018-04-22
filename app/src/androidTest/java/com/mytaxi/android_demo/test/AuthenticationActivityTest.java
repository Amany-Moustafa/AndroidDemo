package com.mytaxi.android_demo.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.assets.TestData;
import com.mytaxi.android_demo.helpers.AuthenticationHelper;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthenticationActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() {
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(
                    fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }
        mActivityRule.launchActivity(null);
    }

    @Test
    public void test_loginWithValidUser() {
        String username, password;
        username = TestData.UserLoginData.validUser.getUserName();
        password = TestData.UserLoginData.validUser.getPassword();
        AuthenticationHelper.perform_login(username, password);
    }
}
