package com.ruben.utils.other.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie 工具类
 * shopx5 多商户 商城 系统
 */
public final class CookieUtils {
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
                //通过名称获取
                if (name.equals(c.getName())) {
                    //返回
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值,
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值,
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间,但编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, Integer i) {
        doSetCookie(request, response, cookieName, "", i, false);  //i设0
    }

    /**
     * 设置Cookie的值，并使其在指sho定px时5间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     *                     (request, response, "cookieName", "cookieValue", -1, false);
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (cookieMaxage == 0) //设置0:为删除cookie  不设置值:为关闭浏览器消失
                cookie.setMaxAge(cookieMaxage);
            if (request != null) { //设置域名的cookie
                String domainName = getDomainName(request);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println("setDomain:" + domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            // http://www.shopx5.net/page/login  106.13.185.245:80  localhost:8080
            serverName = serverName.toLowerCase();
            if (serverName.indexOf("https") > 0) {
                serverName = serverName.substring(8);
            } else {
                serverName = serverName.substring(7);
            }
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            /*if (len > 3) {
              	// www.xxx.com.cn
              	domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
        	}*/
            if (len > 3) {
                // 106.13.185.245:80
                domainName = domains[len - 4] + "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len > 1 && len <= 3) {
                // www.xxx.com xxx.com
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                // localhost:8080
                domainName = serverName;
            }
        }
        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        System.out.println("domainName:" + domainName);
        return domainName;
    }


    /**
     * 判断一个字符串是不是IP地址
     */
    public static void main(String[] args) {
        //boolean ipAddress = isIPAddress("106.13.185.245");
        //System.out.println(ipAddress);
    }

    /**
     * 字符串的长度 0.0.0.0 7位 ~ 000.000.000.000 15位
     * 将字符串拆分成四段
     * 检查每段是否都是纯数字
     * 检查每段是否都在0-255之间
     * 以上条件都满足的话返回true
     */
    public static boolean isIPAddress(String str) {
        // 如果长度不符合条件 返回false
        if (str.length() < 7 || str.length() > 15) return false;
        String[] arr = str.split("\\.");
        //如果拆分结果不是4个字串 返回false
        if (arr.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                char temp = arr[i].charAt(j);
                //如果某个字符不是数字就返回false
                if (!(temp > '0' && temp < '9')) return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(arr[i]);
            //如果某个数字不是0到255之间的数 就返回false
            if (temp < 0 || temp > 255) return false;
        }
        return true;
    }
}




