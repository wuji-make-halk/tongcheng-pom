package com.micro.pmo.commons.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @类功能说明：时间工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：karakal
 * @作者：rao.bo
 * @创建时间：2019.6.25
 * @版本：V1.0
 */
public final class DateTimeUtils {

	public static final String timePattern0 = "yyyyMMdd";
	public static final String timePattern1 = "yyyyMMddHHmmss";
	public static final String timePattern2 = "HHmm";
	public static final String timePattern3 = "yyyyMMddHHmmssSSS";
	public static final String timePattern4 = "yyyy-MM-dd HH:mm:ss";
	public static final String timePattern5 = "yyyy";
	public static final String timePattern6 = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String timePattern7 = "yyyy-MM-dd";
	public static final String timePattern8 = "HH:mm";
	
	public static final String getCurrentYYYYmmddString() {
		return DateFormatUtils.format(new Date(), timePattern0);
	}
	
	public static final String getCurrentYYYYString() {
		return DateFormatUtils.format(new Date(), timePattern5);
	}
	
	public static final String getCurrentHHmmString() {
		return DateFormatUtils.format(new Date(), timePattern2);
	}

	public static final long getDateBySeq(String dateStr) throws ParseException {
		return DateUtils.parseDate(dateStr, timePattern1).getTime();
	}

	public static final String getDateStr() {
		return DateFormatUtils.format(new Date(), timePattern1);
	}
	
	public static final String getDateFormatStr() {
		return DateFormatUtils.format(new Date(), timePattern4);
	}
	
	public static final long getDateNumber() {
		return Long.parseLong(DateFormatUtils.format(new Date(), timePattern3));
	}
	
	public static final Date getPreviousMonthDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}
	
	public static final Date getYesterdayDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	public static final Date getYesterdayDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	public static String format0(Date date) {
		return doFormat(date, timePattern0);
	}

	private static String doFormat(Date date, String pattern) {
		if (date != null && pattern != null) {
			return DateFormatUtils.format(date, pattern);
		}
		return null;
	}

	public static String format4(Date date) {
		return doFormat(date, timePattern4);
	}

	public static String format6(Date date) {
		return doFormat(date, timePattern6);
	}

	public static String format7(Date date) {
		return doFormat(date, timePattern7);
	}
	
	public static String format8(Date date) {
		return doFormat(date, timePattern8);
	}

}
