package com.klarna.weather.network;

import android.support.annotation.Nullable;

public interface Observer<T> {
    /**
     * Called when the data is changed.
     * @param t  The new data
     */
    void onChanged(@Nullable T t);
}