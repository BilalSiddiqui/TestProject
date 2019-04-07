package com.klarna.weather.network;

public class WeatherEndpoints {
    public static String getWeatherUrl(double latitude,double lng){
        return "https://api.darksky.net/forecast/2bb07c3bece89caf533ac9a5d23d8417/"+latitude+","+lng;
    }
}
