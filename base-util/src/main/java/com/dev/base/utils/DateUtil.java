package com.dev.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
		* <p>Title: 时间工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:55:07</p>
 */
public class DateUtil {
	/**
	 * 默认日期格式，格式化到秒
	 */
	public static final String DEFAULT_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认日期格式，格式化到天
	 */
	public static final String DEFAULT_SHORT_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 日期格式，事例：2006年12月21日 星期四
	 */
	public static final String WEEK_FORMAT = "yyyy年MM月dd日 E";
	
	/**
	 * 日期格式，事例：2006年12月21日
	 */
	public static final String DAY_FORMAT = "yyyy年MM月dd日";
	
	/**
	 * 根据指定格式格式化时间
	 * @param format
	 * @param dateStr
	 * @return
	 */
	public static Date parse(String format,String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 格式化时间
	 * @param format
	 * @param date
	 * @return
	 */
	public static String format(String format,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(date);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 格式化到天
	 * @param dateStr
	 * @return
	 */
	public static Date parseByShort(String dateStr){
		return parse(DEFAULT_SHORT_FORMAT, dateStr);
	}
	
	/**
	 * 格式化到秒
	 * @param dateStr
	 * @return
	 */
	public static Date parseByLong(String dateStr){
		return parse(DEFAULT_LONG_FORMAT, dateStr);
	}
	
	/**
	 * 根据时间戳返回时间
	 * @param dateStr
	 * @return
	 */
	public static Date parse(long time){
		return new Date(time);
	}
	
	/**
	 * 格式化到天
	 * @param date
	 * @return
	 */
	public static String formatByShort(Date date){
		return format(DEFAULT_SHORT_FORMAT, date);
	}
	
	/**
	 * 格式化到秒
	 * @param date
	 * @return
	 */
	public static String formatByLong(Date date){
		return format(DEFAULT_LONG_FORMAT, date);
	}

	/**
	 * 获取从现在开始计算的将来某一个时间
	 * 
	 * @param minutes
	 * @return
	 */
	public static Date getNextDate(int minutes) {
		Date date = new Date();
		long currTime = date.getTime();
		currTime = currTime + minutes * 60 * 1000;
		date.setTime(currTime);

		return date;

	}

	/**
	 * 日期转换
	 * 事例：
	 * "2006年12月21日 星期四" 转换成 "2006-12-21"
	 * @param week
	 * @return
	 */
	public static String weekToDay(String dateStr){
		Date date = parse(WEEK_FORMAT, dateStr);
		return formatByShort(date);
	}
	
	/**
	 * 日期转换
	 * 事例：
	 * "2006年12月21日" 转换成 "2006-12-21"
	 * @param dateStr
	 * @return
	 */
	public static String dateToDay(String dateStr){
		Date date = parse(DAY_FORMAT, dateStr);
		return formatByShort(date);
	}
	
	/**
	 * 获取当前时间，默认格式
	 * @return
	 */
	public static String formatNowByLong(){
		return formatByLong(getNow());
	}
	
	/**
	 * 获取当前时间，默认格式
	 * @return
	 */
	public static String formatNowByShort(){
		return formatByShort(getNow());
	}
	
	
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getNow(){
		return new Date();
	}
	
	/**
	 * 获取1970-01-01 00:00:00 到当前时间的毫秒数
	 * @return
	 */
	public static long getNowTime(){
		return getNow().getTime();
	}
	
	/**
	 * 获取指定日期再加上多少小时后的日期时间
	 * @return
	 */
	public static Date getDateAfterHours(Date date, int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, hours*60);
		return c.getTime();
	}
	
	/**
	 * 获取指定日期再减去多少小时后的日期时间
	 * @return
	 */
	public static Date getDateBeforeHours(Date date, int hours) {
		return getDateAfterHours(date, 0 - hours);
	}
	
	/**
	 * 
			*@name 时间戳
			*@Description 
			*@Time 创建时间:2014-4-8下午6:20:45
	 */
	public static long getTime(Date date){
		return date.getTime();
	}
	
	/**
	 * 
			*@name 根据当前时间获取第二天零时零分零秒时间
			*@Description 相关说明 
			*@Time 创建时间:2014-4-8下午7:05:44
	 */
	public static Date getNextDay(Date date){
		return getNextDay(date, 1);
	}
	
	/**
	 * 
			*@name 根据当前时间获取第二天零时零分零秒时间
			*@Description 相关说明 
			*@Time 创建时间:2014-7-25下午1:48:40
	 */
	public static Date getBeforeDay(Date date){
		return getNextDay(date, -1);
	}
	
	/**
	 * 
			*@name 获取指定日期之前或之后指定天数的日期
			*@Description 
			*	num:为正数，则为之后日期
			*		为负数，则为之前日期
			*@Time 创建时间:2014-7-25下午1:46:32
	 */
	public static Date getNextDay(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, num);
		String nextDay = formatByShort(calendar.getTime()) + " 00:00:00";
		
		return parseByLong(nextDay);
	}
	
	/**
	 * 
			*@name 中文名称:long转成Date
			*@Description 相关说明:long转成Date
			*@Time 创建时间:2014-5-24下午4:21:54
	 */
	public static Date getDateByLong(long date) {
		return new Date(date);
	}
	
	/**
	 * 
			*@name 中文名称:两个日期之间相差多少天
			*@Description 相关说明 ：两个日期之间相差多少天
			*@Time 创建时间:2014-7-18上午10:44:41
	 */
	public static int getIntervalDays(String startDate, String endDate) {
		long start = parseByShort(startDate).getTime();
		long end = parseByShort(endDate).getTime();
		return (int)((end-start)/(3600*1000*24));
	}
	
	/**
	 * 
			*@name 中文名称:两个日期之间相差多少天
			*@Description 相关说明 ：两个日期之间相差多少天
			*@Time 创建时间:2014-7-18上午10:44:41
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();
		return (int)((end-start)/(3600*1000*24));
	}
	
	/**
	 * 
			*@name 获得当天0点时间
			*@Description  
			*@CreateDate 2015年10月10日下午5:44:20
	 */
	public static Date getDayStart(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
			*@name 获得当天24点时间
			*@Description  
			*@CreateDate 2015年10月10日下午5:44:29
	 */
	public static Date getDayEnd(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return  cal.getTime();
	}
	
	/**
	 * 
			*@name 获当月第一天0点时间
			*@Description  
			*@CreateDate 2015年10月10日下午5:44:20
	 */
	public static Date getMonthStart(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
			*@name 获得当月最后一天24点时间
			*@Description  
			*@CreateDate 2015年10月10日下午5:44:29
	 */
	public static Date getMonthEnd(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return  cal.getTime();
	}
}