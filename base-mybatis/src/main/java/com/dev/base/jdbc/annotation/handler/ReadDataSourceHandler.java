package com.dev.base.jdbc.annotation.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

import com.dev.base.jdbc.annotation.ReadDataSource;
import com.dev.base.jdbc.datasource.DataSourceContextHolder;

/**
 * 
		* <p>Title: 处理只读注解</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年1月23日上午10:52:23</p>
 */
public class ReadDataSourceHandler {
	/** 默认只读数据源key*/
	private String defaultTargetDataSource;
	
	/**
	 * 
			*@name 设置数据源
			*@Description  
			*@CreateDate 2016年1月23日上午11:18:19
	 */
	public Object doAround(ProceedingJoinPoint joinPoint,ReadDataSource ds) throws Throwable {
		String dataSource = StringUtils.isEmpty(ds.value()) ? defaultTargetDataSource : ds.value(); 
        DataSourceContextHolder.setCurrent(dataSource);
        try{
            return joinPoint.proceed();
        }
        catch(Exception e){
        	e.printStackTrace();
            return null;
        }
        finally{
        	DataSourceContextHolder.clear();
        }
    }
	
	public String getDefaultTargetDataSource() {
		return defaultTargetDataSource;
	}

	public void setDefaultTargetDataSource(String defaultTargetDataSource) {
		this.defaultTargetDataSource = defaultTargetDataSource;
	} 
}
