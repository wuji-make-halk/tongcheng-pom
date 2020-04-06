package com.micro.pmo.commons.utils;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;
/***
 * 获取id
 * @author raoBo
 *
 */
public class UUIDUtils {

	private static String timeTicket; 
	private static int currNum;
	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID 32位字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
	
	/**
	 * 生成流水号
	 * @return
	 */
	public synchronized static String createSerialNum(){
		String ticket = DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		if (!ticket.equals(timeTicket)) {
			timeTicket = ticket;
			currNum = 0;
		}
		currNum++;
		return String.format("%s%04d", timeTicket, currNum);
	}

	/***
	 * MD5 加密
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
