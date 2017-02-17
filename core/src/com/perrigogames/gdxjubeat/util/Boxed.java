package com.perrigogames.gdxjubeat.util;

/**
 * Created by corey on 2/16/17.
 */
public class Boxed<T> {

    public T value;

    public Boxed(T value) {
        this.value = value;
    }

    public String toString() {
        return value == null ? "null" : value.toString();
    }
}
