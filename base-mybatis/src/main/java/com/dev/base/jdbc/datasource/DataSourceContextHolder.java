package com.dev.base.jdbc.datasource;

/**
 * 
		* <p>Title: 保存当前使用的数据源信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年1月23日上午10:53:35</p>
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    /**
     * 
    		*@name 指定当前使用的数据源
    		*@Description  
    		*@CreateDate 2016年1月23日上午10:53:54
     */
    public static void setCurrent(String dataSource) {  
        contextHolder.set(dataSource);  
    }  
      
    /**
     * 
    		*@name 获取当前数据源
    		*@Description  
    		*@CreateDate 2016年1月23日上午10:54:01
     */
    public static String getCurrent() {  
        return contextHolder.get();  
    }  
      
    /**
     * 
    		*@name 清除数据源
    		*@Description  
    		*@CreateDate 2016年1月23日上午10:54:09
     */
    public static void clear() {  
        contextHolder.remove();  
    }  
}
