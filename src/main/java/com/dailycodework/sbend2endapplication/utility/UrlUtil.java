package com.dailycodework.sbend2endapplication.utility;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Sampson Alfred
 */

public class UrlUtil {
    public static String getApplicationUrl(HttpServletRequest request){
        String appUrl = request.getRequestURL().toString();
        return appUrl.replace(request.getServletPath(), "");

    }
}
