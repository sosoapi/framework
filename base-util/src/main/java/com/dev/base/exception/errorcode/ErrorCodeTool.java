package com.dev.base.exception.errorcode;

import java.util.Properties;

/**
 * 
		* <p>Title: 加载错误码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:09:01</p>
 */
public class ErrorCodeTool {

	private static Properties props = new Properties();

	private ErrorCodeTool() {

	}

	public static void setProperty(String key, String value) {
		props.setProperty(key, value);
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}
