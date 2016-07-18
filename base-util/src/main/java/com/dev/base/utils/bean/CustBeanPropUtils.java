package com.dev.base.utils.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import com.dev.base.utils.bean.convert.CustEnumConvert;

/**
 * 
		* <p>Title: bean与map转换</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:34:18</p>
 */
public class CustBeanPropUtils {
	static{
		prepare();
	}
	
	//仅在需强调属性值类型场景使用
	//object -> map:使用PropertyUtils.describe,所有属性值对应类型
	//map -> object:使用BeanUtils.populate,将属性值映射为实体
	private static void prepare(){
		//使用自定义的替换
		PropertyUtilsBean propertyUtilsBean = new CustPropertyUtilsBean();
		ConvertUtilsBean convertUtilsBean = new CustConvertUtilsBean();
		
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,propertyUtilsBean);
		BeanUtilsBean.setInstance(beanUtilsBean);
		
		//注册转换器，处理枚举
		registEnumConvert();
	}
	
	//注册转换器，处理枚举
	private static void registEnumConvert(){
		//日期特殊处理
		CustEnumConvert enumConvert = new CustEnumConvert();
		
		//string -> date的转换，处理BeanUtils.describe中的日期转换
		ConvertUtils.register(enumConvert, Enum.class);
	}
		
	/**
	 * 
			*@name 对象转换为map
			*@Description  对象转换为map
			*@Time 创建时间:2014-6-12下午7:08:57
	 */
	public static Map toMap(Object object){
		Map result = null;
		try {
			result = PropertyUtils.describe(object);
			result.remove("class");
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 
			*@name map转对象 
			*@Description map转对象 
			*@Time 创建时间:2014-6-12下午7:11:39
	 */
	public static <T> T toObject(T object,Map properties){
		try {
			BeanUtils.populate(object, properties);
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return object;
	}
}
