package com.klarna.weather.network;

public interface WebApi {
    void executeRequest(String url,WebApiResponseListener webApiResponseListener);

}
