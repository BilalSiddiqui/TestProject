package com.klarna.weather.network;

public interface WebApiResponseListener {
    void onSuccess(String response);
    void onError(String error);
}
