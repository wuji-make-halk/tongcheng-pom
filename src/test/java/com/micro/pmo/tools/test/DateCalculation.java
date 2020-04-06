package com.micro.pmo.tools.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateCalculation {

	@Test
	public void carCalculation() throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date ss = formatter.parse("2019-07-10");
		long old = ss.getTime();
		long nowDate = new Date().getTime();
		long cha = nowDate - old;
		long day = cha/(1000*60*60*24);
		System.out.println((int)day);
	}
}
