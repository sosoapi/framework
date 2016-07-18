package com.dev.base.utils.bean.convert;

import java.util.Date;

import org.apache.commons.beanutils.Converter;

import com.dev.base.utils.DateUtil;

/**
 * 
		* <p>Title: 自定义日期类型</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:33:45</p>
 */
public class CustDateConvert implements Converter{
	//判断是否是日期格式字符串
	private boolean isDate(String value){
		if (value == null) {
			return false;
		}
		
		return value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
	}
	
	//处理字符串
	private Object dealString(String value){
		return isDate(value) ? DateUtil.parseByLong(value) : value;
	}
	
	@Override
	public Object convert(Class clazz, Object value) {
		if (value == null) {
			return null;
		}
		
		//处理date -> string
		if (value instanceof Date) {
			return DateUtil.formatByLong((Date)value);
		}
		//处理string -> date
		else if (value instanceof String) {
			return dealString((String)value);
		}
		
		return value.toString();
	}
}

