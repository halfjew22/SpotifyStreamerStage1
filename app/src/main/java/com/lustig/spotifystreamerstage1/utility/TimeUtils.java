package com.lustig.spotifystreamerstage1.utility;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String minutesSecondsFromMilliseconds(long timeInMilliseconds) {

        String timeMinutesSeconds = String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMilliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds)));

        return timeMinutesSeconds;
    }

}
