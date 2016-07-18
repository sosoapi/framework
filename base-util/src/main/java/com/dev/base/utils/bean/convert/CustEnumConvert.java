package com.dev.base.utils.bean.convert;

import org.apache.commons.beanutils.Converter;

/**
 * 
		* <p>Title: 自定义枚举转换</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:34:00</p>
 */
public class CustEnumConvert implements Converter{

	@Override
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		
		if (type.isEnum()) {
			return Enum.valueOf(type, value.toString());
		}
		
		return value;
	}
}
