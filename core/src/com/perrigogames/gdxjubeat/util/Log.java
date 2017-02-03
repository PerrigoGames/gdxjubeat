package com.perrigogames.gdxjubeat.util;

/**
 * Created by corey on 2/3/17.
 */
public class Log {

    private static StringBuilder builder = new StringBuilder();

    public static void v (String content) {
        System.out.printf(content + "\n");
    }

    public static void v (String content, Object ... args) {
        System.out.printf(content + "\n", args);
    }

    private static String addLogInfo (String clazz, int line, String content) {
        builder.delete(0, builder.length());
//        builder.append(String.format());
        return builder.toString();
    }
}
