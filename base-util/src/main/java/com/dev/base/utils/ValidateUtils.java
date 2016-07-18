package com.dev.base.utils;

import org.apache.commons.lang.StringUtils;

import com.dev.base.exception.ValidateException;

/**
 * 
		* <p>Title: 提供输入参数验证的方法</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:46:47</p>
 */
public class ValidateUtils {
	/**
	 * 
			*@name 验证传入对象是否为空对象或者空字符串
			*@Description  
			*@CreateDate 2015年8月21日下午2:46:59
	 */
	public static void notNull(Object o, String errorCode) {
		if (o == null || StringUtils.isBlank(o.toString())) {
			throw new ValidateException(errorCode);
		}
	}

	/**
	 * 
			*@name 验证传入对象是否为空对象或者空字符串
			*@Description  
			*@CreateDate 2015年8月21日下午2:47:15
	 */
	public static void notNull(Object o, String errorCode, String errorTip) {
		if (o == null || StringUtils.isBlank(o.toString())) {
			throw new ValidateException(errorCode, errorTip);
		}
	}

	/**
	 * 
			*@name 判断传入的逻辑表达式是否为真
			*@Description  
			*@CreateDate 2015年8月21日下午2:47:24
	 */
	public static void isTrue(boolean condition, String errorCode) {
		if (!condition) {
			throw new ValidateException(errorCode);
		}
	}

	/**
	 * 
			*@name 判断传入的逻辑表达式是否为真
			*@Description  
			*@CreateDate 2015年8月21日下午2:47:33
	 */
	public static void isTrue(boolean condition, String errorCode,
			String errorTip) {
		if (!condition) {
			throw new ValidateException(errorCode, errorTip);
		}
	}
}
