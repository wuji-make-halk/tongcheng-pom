package com.micro.pmo.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public final class CommonUtils {

	public static final String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static final int getRandomNum6() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

	public static final String md5(String orginalStr) {
		return DigestUtils.md5Hex(orginalStr);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 检查字符串长度
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean isTooLarge(String str, int length) {
		if (str == null)
			return false;

		return str.length() > length;
	}

	private static boolean equal(String str, int length) {
		if (str == null) {
			return false;
		}
		return str.length() == length;
	}

	public static boolean notEqual(String str, int length) {
		return !equal(str, length);
	}

	public static String cutString(String str, int length) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.length() > length) {
			return str.substring(0, length);
		} else {
			return str;
		}
	}
	
	public static List<String> toList(String... strings) {
		if (ArrayUtils.isEmpty(strings)) {
			return null;
		}
		List<String> list = new ArrayList<String>(strings.length);
		for (int i = 0, s = strings.length; i < s; i++) {
			list.add(strings[i]);
		}
		return list;
	}

}
