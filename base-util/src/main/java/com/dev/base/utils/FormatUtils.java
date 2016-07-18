package com.dev.base.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.dev.base.exception.errorcode.SysErrorCode;

/**
 * 
		* <p>Title: 格式化工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:52:41</p>
 */
public class FormatUtils {

	/**
	 * 
			*@name 校验是否为电话号码
			*@Description  
			*@CreateDate 2015年8月21日下午2:52:56
	 */
	public static boolean checkPhoneNum(String phoneNum) {
		Pattern p1 = Pattern.compile("^((\\+{0,1}86){0,1})1[0-9]{10}");
		Matcher m1 = p1.matcher(phoneNum);
		
		return m1.matches();
	}

	/**
	 * 
			*@name string转成Long
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:03
	 */
	public static Long getLong(String str, String errorMsg) {
		Long id = null;
		try {
			id = Long.parseLong(str);
		} catch (Exception e) {
			ValidateUtils.isTrue(false, SysErrorCode.SYS_006, errorMsg);
		}
		return id;
	}
	
	/**
	 * 
			*@name string转成Long
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:12
	 */
	public static Long getLong(String str) {
		return getLong(str, null);
	}
	
	/**
	 * 
			*@name string转成Integer
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:20
	 */
	public static Integer getInt(String str) {
		Integer id = null;
		try {
			id = Integer.parseInt(str);
		} catch (Exception e) {
			ValidateUtils.isTrue(false, SysErrorCode.SYS_006);
		}
		return id;
	}
	
	/**
	 * 
			*@name string转成Long
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:28
	 */
	public static Long parseLong(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		
		return getLong(str, null);
	}
	
	/**
	 * 
			*@name 转换int，异常则返回默认值
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:36
	 */
	public static int parseInt(String str,int def){
		int value = def;
		try {
			value = Integer.parseInt(str);
		} catch (Exception e) {
			
		}
		return value;
	}
	
	/**
	 * 
			*@name 转换double，异常则返回默认值
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:44
	 */
	public static double parseDouble(String str,double def){
		double value = def;
		try {
			value = Double.parseDouble(str);
		} catch (Exception e) {
			
		}
		return value;
	}
	
	/**
	 * 
			*@name 适用于数值经过json转换为int再强制转换为double报错场景
			*@Description  
			*@CreateDate 2015年8月21日下午2:53:54
	 */
	public static double parseDouble(Object object){
		Number number = (Number)object;
		return number.doubleValue();
	}
	
	/**
	 * 
			*@name 判断数字
			*@Description  
			*@CreateDate 2015年8月21日下午2:54:02
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 }
	
	/**
	 * 
			*@name 格式化double
			*@Description 相关说明 
			*@Time 创建时间:2014-4-24下午6:49:23
			*@param value	需要进行格式化的值
			*@param scale	需要保留的小数位数
	 */
	public static double formatDouble(double value,int scale) {
		BigDecimal bg = new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP);
        return bg.doubleValue();
    }
	
	/**
	 * 
			*@name 将map中的value转换为Integer
			*@Description 
			*@Time 创建时间:2014-5-9下午4:24:58
	 */
	public static Long parseLong(Object object){
		return ((Number)object).longValue();
	}
	
	public static Integer parseInt(Object object){
		return ((Number)object).intValue();
	}
	
	public static Long parseLong(String str,long def){
		long value = def;
		try {
			value = Long.parseLong(str);
		} catch (Exception e) {
			
		}
		
		return value;
	}
	
	/**
	 * 
			*@name 比较
			*@Description 相关说明 
			*@Time 创建时间:2014年8月20日上午10:58:40
	 */
	public static boolean isEqual(Long src,Long dest){
		if (src == null || dest == null) {
			return false;
		}
		
		return src.longValue() == dest.longValue();
	}
	
	/**
	 * 
			*@name 日期比较
			*@Description  
			*@CreateDate 2016年4月9日上午10:22:24
	 */
	public static boolean compareDate(Date src,Date dist){
		return src.getTime() >= dist.getTime();
	}
}
