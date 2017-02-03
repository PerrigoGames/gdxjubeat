package com.perrigogames.gdxjubeat.util;

/**
 * Created by corey on 2/3/17.
 */
public class L {

    public static void $(Listener l) {
        if (l != null) l.invoke();
    }

    public static <A> void $(Listener1 l, A arg1) {
        if (l != null) l.invoke(arg1);
    }

    public static <A,B> void $(Listener2 l, A arg1, B arg2) {
        if (l != null) l.invoke(arg1, arg2);
    }

    public static <A,B,C> void $(Listener3 l, A arg1, B arg2, C arg3) {
        if (l != null) l.invoke(arg1, arg2, arg3);
    }

    public interface Listener {
        void invoke();
    }

    public interface Listener1<A> {
        void invoke(A arg1);
    }

    public interface Listener2<A,B> {
        void invoke(A arg1, B arg2);
    }

    public interface Listener3<A,B,C> {
        void invoke(A arg1, B arg2, C arg3);
    }
}
