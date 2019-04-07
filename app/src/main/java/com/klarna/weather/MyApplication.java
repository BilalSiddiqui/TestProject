package com.klarna.weather;

import android.app.Application;

import com.klarna.weather.network.OkHttpWebClient;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpWebClient.getInstance().init(getApplicationContext());
    }
}

