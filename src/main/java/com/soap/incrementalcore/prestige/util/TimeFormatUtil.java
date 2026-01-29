package com.soap.incrementalcore.prestige.util;

public class TimeFormatUtil {

    public static String  ticksMMSS(long ticksAmount) {
        long totalSeconds = ticksAmount / 20;

        int minutes = (int)(totalSeconds / 60);
        int seconds = (int)(totalSeconds % 60);

        return String.valueOf(minutes) + " : " + String.valueOf(seconds);
    }
}
