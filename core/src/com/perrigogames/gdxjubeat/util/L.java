package com.perrigogames.gdxjubeat.util;

/**
 * Created by corey on 2/3/17.
 */
public class L {

    public static void $(Listener l) {
        if (l != null) l.invoke();
    }

    public static <A> void $(Listener1<A> l, A arg1) {
        if (l != null) l.invoke(arg1);
    }

    public static <A,B> void $(Listener2<A,B> l, A arg1, B arg2) {
        if (l != null) l.invoke(arg1, arg2);
    }

    public static <A,B,C> void $(Listener3<A,B,C> l, A arg1, B arg2, C arg3) {
        if (l != null) l.invoke(arg1, arg2, arg3);
    }

    public static <A,B,C,D> void $(Listener4<A,B,C,D> l, A arg1, B arg2, C arg3, D arg4) {
        if (l != null) l.invoke(arg1, arg2, arg3, arg4);
    }

    public static <A,B,C,D,E> void $(Listener5<A,B,C,D,E> l, A arg1, B arg2, C arg3, D arg4, E arg5) {
        if (l != null) l.invoke(arg1, arg2, arg3, arg4, arg5);
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

    public interface Listener4<A,B,C,D> {
        void invoke(A arg1, B arg2, C arg3, D arg4);
    }

    public interface Listener5<A,B,C,D,E> {
        void invoke(A arg1, B arg2, C arg3, D arg4, E arg5);
    }

    public static <A> A $(Func<A> l) {
        return (l != null) ? l.invoke() : null;
    }

    public static <A,B> A $(Func1<A,B> l, B arg1) {
        return (l != null) ? l.invoke(arg1) : null;
    }

    public static <A,B,C> A $(Func2<A,B,C> l, B arg1, C arg2) {
        return (l != null) ? l.invoke(arg1, arg2) : null;
    }

    public static <A,B,C,D> A $(Func3<A,B,C,D> l, B arg1, C arg2, D arg3) {
        return (l != null) ? l.invoke(arg1, arg2, arg3) : null;
    }

    public static <A,B,C,D,E> A $(Func4<A,B,C,D,E> l, B arg1, C arg2, D arg3, E arg4) {
        return (l != null) ? l.invoke(arg1, arg2, arg3, arg4) : null;
    }

    public static <A,B,C,D,E,F> A $(Func5<A,B,C,D,E,F> l, B arg1, C arg2, D arg3, E arg4, F arg5) {
        return (l != null) ? l.invoke(arg1, arg2, arg3, arg4, arg5) : null;
    }

    public interface Func<A> {
        A invoke();
    }

    public interface Func1<A,B> {
        A invoke(B arg1);
    }

    public interface Func2<A,B,C> {
        A invoke(B arg1, C arg2);
    }

    public interface Func3<A,B,C,D> {
        A invoke(B arg1, C arg2, D arg3);
    }

    public interface Func4<A,B,C,D,E> {
        A invoke(B arg1, C arg2, D arg3, E arg4);
    }

    public interface Func5<A,B,C,D,E,F> {
        A invoke(B arg1, C arg2, D arg3, E arg4, F arg5);
    }
}
