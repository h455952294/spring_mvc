package com.core.common.web;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;

/**
 * 操作Cookie的工具类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月10日 上午10:08:00
 * @version 1.0
 */
public final class CookieTools {
	
	/**
	 * 添加Cookie
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param maxAge 存活时长(按秒计算)
	 */
	public static void addCookie(String cookieName, String cookieValue, int maxAge){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie == null){
			/** 创建Cookie */
			cookie = new Cookie(cookieName, cookieValue);
		}
		/** 设置Cookie的存活时长 */
		cookie.setMaxAge(maxAge);
		/** 设置Cookie的访问路径  http://localhost:8080/*/
		cookie.setPath("/");
		/** 设置Cookie可以跨子域访问  www.fkjava.org  t.fkjava.org*/
		//cookie.setDomain(".fkjava.org");
		ServletActionContext.getResponse().addCookie(cookie);
	}
	
	/**
	 * 获取Cookie
	 * @param cookieName 名称
	 * @return Cookie
	 */
	public static Cookie getCookie(String cookieName){
		/** 获取所有的Cookie */
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies){
				if (cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 删除Cookie
	 * @param cookieName 名称
	 */
	public static void removeCookie(String cookieName){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie != null){
			/** 设置cookie失效 */
			cookie.setMaxAge(0);
			/** 设置Cookie的访问路径  http://localhost:8080/*/
			cookie.setPath("/");
			/** 添加Cookie到用户浏览器 */
			ServletActionContext.getResponse().addCookie(cookie);
		}
	}
}
