package com.perrigogames.gdxjubeat.util;

/** Class to hold a reference to a value.
 * Created by corey on 2/16/17. */
public class Boxed<T> {

    public T value;

    public Boxed(T value) {
        this.value = value;
    }

    public String toString() {
        return value == null ? "null" : value.toString();
    }
}
