package com.dev.base.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
		* <p>Title: 数据库只读操作</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年1月23日上午10:51:57</p>
 */
@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface ReadDataSource {
	/** 数据源key*/
	String value() default "";
}
