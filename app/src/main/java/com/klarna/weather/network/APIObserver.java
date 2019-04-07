package com.klarna.weather.network;

public class APIObserver<T> {
    private T t;
    private Observer<T> observer;

    final public T getValue() {
        return t;
    }

    final public void setValue(T t) {
        this.t = t;
        observer.onChanged(t);
    }

    public void observer(Observer<T> observer) {
        this.observer = observer;
    }
}