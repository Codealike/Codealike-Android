package com.codealike.android;

import android.app.Application;
import android.util.Log;

import com.codealike.android.model.UserData;

/**
 * Created by root on 8/11/14.
 */
public class CodealikeApplication extends Application {

    private UserData userData;

    public UserData getUserData()
    {
        return userData;
    }

    public void setUserData(UserData userData)
    {
        this.userData = userData;
    }

    private static final String TAG = "CodealikeApplication";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }
}
