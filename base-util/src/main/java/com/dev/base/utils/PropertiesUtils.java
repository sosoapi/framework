package com.dev.base.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
		* <p>Title: Property工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:49:55</p>
 */
public class PropertiesUtils {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	/**
	 * 获取指定的资源对象
	 * @param propertiesFilePath
	 * @return
	 */
	public static Properties getProperties(String propertiesFilePath){
		Properties properties = null;
		try {
			logger.info("加载资源[" + propertiesFilePath + "] ...");
			
			properties = PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource(propertiesFilePath), "UTF-8"));
		} 
		catch (IOException e) {
			logger.error("加载资源[" + propertiesFilePath + "]失败");
			logger.error(e.getMessage());

			e.printStackTrace();
		}
		
		return properties;
	}
}