package com.mytaxi.android_demo;

import android.content.Context;

import com.mytaxi.android_demo.dependencies.component.AppComponent;
import com.mytaxi.android_demo.dependencies.component.DaggerAppComponent;
import com.mytaxi.android_demo.dependencies.module.NetworkModule;
import com.mytaxi.android_demo.dependencies.module.SharedPrefStorageModule;
import com.mytaxi.android_demo.models.User;
import com.mytaxi.android_demo.utils.storage.SharedPrefStorage;

import org.mockito.Mockito;

import javax.annotation.Nonnull;

import dagger.Module;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestsApp extends App {


    protected AppComponent initializeAppComponent() {
        return DaggerAppComponent.builder()
                .sharedPrefStorageModule(new MockSharedPrefStorageModule(this))
                .build();
    }

    @Module
    private class MockSharedPrefStorageModule extends SharedPrefStorageModule {

        MockSharedPrefStorageModule(@Nonnull final App app) {
            super(app);
        }


        public SharedPrefStorage appPreferences(final Context context) {
            final SharedPrefStorage mock = Mockito.mock(SharedPrefStorage.class);
            User mockedUser =  Mockito.mock(User.class);
            when(mockedUser.match(anyString(),anyString())).thenReturn(true);
            when(mock.loadUser()).thenReturn(mockedUser);
            return mock;
        }
    }


}