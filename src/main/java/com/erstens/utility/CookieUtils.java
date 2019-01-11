package com.erstens.utility;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;

public class CookieUtils {
    public static void removeCookie() {
        HttpServletResponse rep = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        Cookie c = new Cookie("token", null);
        c.setMaxAge(0);
        rep.addCookie(c);
    }

    public static String getCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse("");
    }
    public static void saveCookie(String txt) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        response.addCookie(new Cookie("token",Base64.getEncoder().encodeToString(txt.getBytes())));
    }
}
