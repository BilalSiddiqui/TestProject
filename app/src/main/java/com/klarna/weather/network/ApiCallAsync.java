package com.klarna.weather.network;

import android.os.AsyncTask;

public class ApiCallAsync extends AsyncTask<String,Void,Void> {
    private String mUrl;
    private WebApiResponseListener mListener;

    public ApiCallAsync(WebApiResponseListener listener){
        mListener=listener;
    }

    @Override
    protected Void doInBackground(String... webApiResponseListeners) {


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
