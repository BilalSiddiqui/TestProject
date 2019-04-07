package com.klarna.weather.data;

import android.util.Log;

import com.klarna.weather.network.APIObserver;
import com.klarna.weather.network.OkHttpWebClient;
import com.klarna.weather.network.WeatherEndpoints;
import com.klarna.weather.network.WebApiResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Repository {
    public static final String TAG = Repository.class.getSimpleName();
    private static final Repository ourInstance = new Repository();
    APIObserver<String> mErrorObserver = new APIObserver<>();
    APIObserver<HashMap<String, String>> mWeatherObserver = new APIObserver<>();

    private Repository() {
    }

    public static Repository getInstance() {
        return ourInstance;
    }

    public void getLatestWeather(double lat, double lng) {
        OkHttpWebClient.getInstance().executeRequest(WeatherEndpoints.getWeatherUrl(lat, lng), new WebApiResponseListener() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "response: " + response);
                HashMap<String, String> weatherMap = new HashMap<>();
                try {
                    JSONObject resJson = new JSONObject(response);
                    if (resJson.has("timezone")) {
                        weatherMap.put("timeZone", resJson.getString("timezone"));
                    }
                    if (resJson.has("currently")) {
                        JSONObject currentlyJson = resJson.getJSONObject("currently");
                        if (currentlyJson.has("time")) {
                            weatherMap.put("time", currentlyJson.getString("time"));
                        }

                        if (currentlyJson.has("icon")) {
                            weatherMap.put("icon", currentlyJson.getString("icon"));
                        }

                        if (currentlyJson.has("precipIntensity")) {
                            weatherMap.put("precipIntensity", currentlyJson.getString("precipIntensity"));
                        }

                        if (currentlyJson.has("precipProbability")) {
                            weatherMap.put("precipProbability", currentlyJson.getString("precipProbability"));
                        }

                        if (currentlyJson.has("temperature")) {
                            weatherMap.put("temperature", currentlyJson.getString("temperature"));
                        }

                        if (currentlyJson.has("apparentTemperature")) {
                            weatherMap.put("apparentTemperature", currentlyJson.getString("apparentTemperature"));
                        }

                        if (currentlyJson.has("dewPoint")) {
                            weatherMap.put("dewPoint", currentlyJson.getString("dewPoint"));
                        }

                        if (currentlyJson.has("humidity")) {
                            weatherMap.put("humidity", currentlyJson.getString("humidity"));
                        }

                        if (currentlyJson.has("pressure")) {
                            weatherMap.put("pressure", currentlyJson.getString("pressure"));
                        }

                        if (currentlyJson.has("windSpeed")) {
                            weatherMap.put("windSpeed", currentlyJson.getString("windSpeed"));
                        }
                        if (currentlyJson.has("uvIndex")) {
                            weatherMap.put("uvIndex", currentlyJson.getString("uvIndex"));
                        }
                        if (currentlyJson.has("windGust")) {
                            weatherMap.put("windGust", currentlyJson.getString("windGust"));
                        }
                        if (currentlyJson.has("windBearing")) {
                            weatherMap.put("windBearing", currentlyJson.getString("windBearing"));
                        }
                        if (currentlyJson.has("cloudCover")) {
                            weatherMap.put("cloudCover", currentlyJson.getString("cloudCover"));
                        }
                        if (currentlyJson.has("visibility")) {
                            weatherMap.put("visibility", currentlyJson.getString("visibility"));
                        }
                        if (currentlyJson.has("ozone")) {
                            weatherMap.put("ozone", currentlyJson.getString("ozone"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWeatherObserver.setValue(weatherMap);
            }

            @Override
            public void onError(String error) {
                mErrorObserver.setValue(error);
            }
        });
    }


    public APIObserver<String> getError() {
        return mErrorObserver;
    }


    public APIObserver<HashMap<String, String>> getObserver() {
        return mWeatherObserver;
    }
}
