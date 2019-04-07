package com.klarna.weather.network;

import android.content.Context;
import android.os.AsyncTask;

import com.klarna.weather.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * The purpose of this class it to provide network calls functionality
 * with low coupling on library used so that you can change it anytime without
 * changing the code of complete application
 */
public class OkHttpWebClient implements WebApi {
    private static OkHttpWebClient sClientInstance = new OkHttpWebClient();
    private Context mContext;
    private OkHttpClient client;


    private OkHttpWebClient() {
        client = new OkHttpClient();
    }

    public static OkHttpWebClient getInstance() {
        return sClientInstance;
    }

    public void init(Context context) {
        getInstance().mContext = context;
    }

    @Override
    public void executeRequest(String url, WebApiResponseListener listener) {
        if (Utils.isInternetConnected(mContext)) {
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        return response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);

                    if (o == null) {
                        listener.onError(mContext.getString(R.string.error_general));
                    } else {
                        listener.onSuccess((String) o);
                    }
                }
            }.execute();


        } else {
            listener.onError(mContext.getString(R.string.error_no_internet));
        }

    }

}
