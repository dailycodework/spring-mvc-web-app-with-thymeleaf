package com.dailycodework.sbend2endapplication.utility;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Sampson Alfred
 */

public class TokenExpirationTime {
    private static final int EXPIRATION_TIME = 10;

    public static Date getExpirationTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
