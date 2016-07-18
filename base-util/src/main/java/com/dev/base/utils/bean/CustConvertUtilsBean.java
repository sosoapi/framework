package com.dev.base.utils.bean;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 * 
		* <p>Title: 重写，提供对枚举的处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:45:06</p>
 */
public class CustConvertUtilsBean extends ConvertUtilsBean{
	@Override
	public Converter lookup(Class clazz) {
    	//如果是枚举类型则设置为Enum.class
    	if (clazz.isEnum()) {
			clazz = Enum.class;
		}
    	
        return super.lookup(clazz);
    }
}
