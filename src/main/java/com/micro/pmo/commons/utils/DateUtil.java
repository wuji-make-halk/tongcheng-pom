package com.micro.pmo.commons.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd
	 */
	public static final int DEFAULT = 0;
	public static final int YM = 1;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd
	 * 
	 */
	public static final int YMR_SLASH = 11;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMdd
	 * 
	 */
	public static final int NO_SLASH = 2;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMM
	 * 
	 */
	public static final int YM_NO_SLASH = 3;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
	 * 
	 */
	public static final int DATE_TIME = 4;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
	 * 
	 */
	public static final int DATE_TIME_NO_SLASH = 5;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
	 * 
	 */
	public static final int DATE_HM = 6;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm:ss
	 * 
	 */
	public static final int TIME = 7;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm
	 * 
	 */
	public static final int HM = 8;

	/**
	 * 变量：日期格式化类型 - 格式:HHmmss
	 * 
	 */
	public static final int LONG_TIME = 9;
	/**
	 * 变量：日期格式化类型 - 格式:HHmm
	 * 
	 */

	public static final int SHORT_TIME = 10;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
	 */
	public static final int DATE_TIME_LINE = 12;

	public static String dateToStr(Date date, int type) {
		switch (type) {
		case DEFAULT:
			return dateToStr(date);
		case YM:
			return dateToStr(date, "yyyy/MM");
		case NO_SLASH:
			return dateToStr(date, "yyyyMMdd");
		case YMR_SLASH:
			return dateToStr(date, "yyyy-MM-dd");
		case YM_NO_SLASH:
			return dateToStr(date, "yyyyMM");
		case DATE_TIME:
			return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
		case DATE_TIME_NO_SLASH:
			return dateToStr(date, "yyyyMMddHHmmss");
		case DATE_HM:
			return dateToStr(date, "yyyy/MM/dd HH:mm");
		case TIME:
			return dateToStr(date, "HH:mm:ss");
		case HM:
			return dateToStr(date, "HH:mm");
		case LONG_TIME:
			return dateToStr(date, "HHmmss");
		case SHORT_TIME:
			return dateToStr(date, "HHmm");
		case DATE_TIME_LINE:
			return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
		default:
			throw new IllegalArgumentException("Type undefined : " + type);
		}
	}

	public static String dateToStr(Date date, String pattern) {
		if (date == null || date.equals(""))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	/**
	 * 根据1970年距今的毫秒数 计算剩余月数,保留一位小数
	 * 
	 * @param endMillis
	 * @return
	 */
	public static BigDecimal getSurplusMonth(Date endDate) {
		if (endDate == null) {
			return BigDecimal.ZERO;
		}
		long surplusMillisTime = endDate.getTime() - System.currentTimeMillis();

		if (surplusMillisTime <= 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal millisTime = BigDecimal.valueOf(surplusMillisTime);

		BigDecimal surplusMonth = millisTime.divide(BigDecimal.valueOf(1000L * 60 * 60 * 24 * 30), 1,
				BigDecimal.ROUND_HALF_UP);

		return surplusMonth;
	}

	/**
	  * 为date添加指定天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date datePlusDays(Date date, int days) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
		date = ca.getTime();

		return date;
	}

	/**
	  * 为date添加指定天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date datePlusMonth(Date date ,Integer month) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, month);
		date = ca.getTime();

		return date;
	}
	
	public static Date localDateToDate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	public static LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate;
	}

}
