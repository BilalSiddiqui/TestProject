package com.klarna.weather.Views;

import com.klarna.weather.data.Repository;
import com.klarna.weather.network.APIObserver;

import java.util.HashMap;

public class ViewModel {
    private static final ViewModel ourInstance = new ViewModel();
    Repository mRepository;

    private ViewModel() {
        mRepository = Repository.getInstance();
    }

    public static ViewModel getInstance() {
        return ourInstance;
    }

    public void getCurrentWeather(double lat, double lng) {
        mRepository.getLatestWeather(lat, lng);
    }

    public APIObserver<HashMap<String, String>>  getWeatherUpdates() {
       return mRepository.getObserver();
    }

    public APIObserver<String> getErrorUpdates() {
        return mRepository.getError();
    }
}
