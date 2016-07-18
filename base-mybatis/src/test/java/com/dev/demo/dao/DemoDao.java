package com.dev.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.demo.entity.Demo;

/**
 * 
		* <p>Title: demo</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:36:51</p>
 */
public interface DemoDao extends BaseMybatisDao<Demo, Long> {
	/**
	 * 
			*@name 循环测试
			*@Description  
			*@CreateDate 2015年8月21日下午1:37:07
	 */
	List<Demo> foreach(Map paramMap);
	
	/**
	 * 
			*@name 查询
			*@Description  
			*@CreateDate 2015年8月21日下午1:37:12
	 */
	Demo find(@Param("name")String name,@Param("age")int age);
}
