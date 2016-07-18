package com.dev.base.utils;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testGetDayStart() {
		printDate(DateUtil.getDayStart(DateUtil.getNow()));
	}

	@Test
	public void testGetDayEnd() {
		printDate(DateUtil.getDayEnd(DateUtil.getNow()));
	}

	@Test
	public void testGetMonthStart() {
		printDate(DateUtil.getMonthStart(DateUtil.getNow()));
	}

	@Test
	public void testGetMonthEnd() {
//		printDate(DateUtil.getMonthEnd(DateUtil.getNow()));
		printDate(DateUtil.getMonthEnd(DateUtil.parseByLong("2015-02-03 12:00:00")));
	}

	private void printDate(Date date){
		System.out.println(DateUtil.formatByLong(date));
	}
}
