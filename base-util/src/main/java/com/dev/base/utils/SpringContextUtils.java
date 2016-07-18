package com.dev.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 
		* <p>Title: spring工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:48:26</p>
 */
@Component
@Lazy(value = false)
public class SpringContextUtils implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	/**
	 * 根据名称获取容器中指定名称的bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}

	/**
	 * 
			*@name 根据类型获取指定的bean
			*@Description 相关说明 
			*@Time 创建时间:2015年6月15日下午3:38:19
			*@param requiredType
	 */
	public static <T> T getBean(Class<T> requiredType){
		return context.getBean(requiredType);
	}
	
	/**
	 * 获取spring 容器上下文
	 * @return
	 */
	public ApplicationContext getApplicationContext(){
		return context;
	}
}

