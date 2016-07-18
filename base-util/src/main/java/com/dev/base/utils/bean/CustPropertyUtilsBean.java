package com.dev.base.utils.bean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * 
		* <p>Title: 重写，对枚举的支持，将枚举反射为string</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:44:52</p>
 */
public class CustPropertyUtilsBean extends PropertyUtilsBean{
	@Override
	public Object getSimpleProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException{
		Object object = super.getSimpleProperty(bean, name);
		if (object instanceof Enum) {
			object = object.toString();
		}
		
		return object;
	}
}
