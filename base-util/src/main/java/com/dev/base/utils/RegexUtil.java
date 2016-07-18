package com.dev.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
		* <p>Title: 正则表达式工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午2:02:59</p>
 */
public class RegexUtil {
	private static final String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	private static final String REGEX_IP = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
	private static final String REGEX_POST_CODE = "^[1-9]{1}\\d{5}$";
	private static final String REGEX_ID_CARD = "^(\\d{14}|\\d{17})(\\d|[xX])$";
	private static final String REGEX_MOBILE = "^(0)?[1]\\d{10}$";

	/**
	 * 验证邮箱格式
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		return regex(str,REGEX_EMAIL);
	}

	/**
	 * 验证ip格式
	 * @param str
	 * @return
	 */
	public static boolean isIp(String str) {
		return regex(str, REGEX_IP);
	}

	/**
	 * 验证固话格式
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		return regex(str, REGEX_MOBILE);
	}

	/**
	 * 验证post卡格式
	 * @param str
	 * @return
	 */
	public static boolean isPostCode(String str) {
		return regex(str, REGEX_POST_CODE);
	}

	/**
	 * 验证id卡格式
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str) {
		return regex(str, REGEX_ID_CARD);
	}

	/**
	 * 验证指定字符串是否匹配相应正则表达式
	 * @param testStr
	 * @param regStr
	 * @return
	 * @throws Exception
	 */
	public static final boolean regex(String testStr, String regStr){
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(testStr);
		return m.matches();
	}

	/**
	 * 获取匹配指定正则表达式的字符串
	 * @param testStr
	 * @param regStr
	 * @param defaultValue
	 * @return
	 */
	public static final String getMatchValue(String testStr, String regStr,String defaultValue) {
		try {
			Pattern p = Pattern.compile(regStr);
			Matcher m = p.matcher(testStr);
			if (m.find()){
				return m.group(1);
			}
		} catch (Exception e) {
			return defaultValue;
		}
		return defaultValue;
	}
}