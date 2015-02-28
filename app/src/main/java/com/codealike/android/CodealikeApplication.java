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

    private String userName; //= "hveiras";

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    private String token = "196f999e-e860-4687-9122-1bbfa4802ae0";

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    private static final String TAG = "CodealikeApplication";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }
}
