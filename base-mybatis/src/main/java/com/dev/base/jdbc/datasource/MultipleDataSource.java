package com.dev.base.jdbc.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
		* <p>Title: 自定义多数据源</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年1月23日上午10:53:11</p>
 */
public class MultipleDataSource extends AbstractRoutingDataSource{
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getCurrent();
	}
}
