package com.ai.spring.cloud.zuul.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Util class for web application
 */
public final class WebUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static boolean shouldFilter(String url) {
        return !(url.startsWith("/sso") || url.startsWith("/assets"));
    }

    /**
     * Get value from cookie
     * @param name
     * @param request
     * @return
     */
    public static String getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
