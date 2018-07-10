package com.lightwing.store.utils;

import javax.servlet.http.Cookie;

public class CookUtils {
    /**
     * 通过名称在cookie数组获取指定的cookie
     *
     * @param name    cookie名称
     * @param cookies cookie数组
     * @return
     */
    public static Cookie getCookieByName(String name, Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    return c;
                }
            }
            return null;
        }
        return null;
    }
}
