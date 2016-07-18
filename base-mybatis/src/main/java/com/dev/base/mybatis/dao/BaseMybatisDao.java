package com.dev.base.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 数据库基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:31:18</p>
 */
public interface BaseMybatisDao<T extends BaseMybatisEntity,ID extends Serializable>{
	/**
	 * 分页开始第一条记录索引
	 */
	public final static String KEY_PAGE_START = "pageStart";
	
	/**
	 *分页请求记录数 
	 */
	public final static String KEY_PAGE_SIZE = "pageSize";
	
	/**
	 * 
			*@name 新增对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:31:38
	 */
	void add(T param);

	/**
	 * 
			*@name 批量新增
			*@Description  
			*@CreateDate 2015年8月21日下午1:32:20
	 */
	void batchAdd(List<T> batchList);
	
	/**
	 * 
			*@name 修改对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:32:29
	 */
	void update(T param);

	/**
	 * 
			*@name 删除对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:32:38
	 */
	void delete(T param);

	/**
	 * 
			*@name 根据id进行删除
			*@Description  
			*@CreateDate 2015年8月21日下午1:32:47
	 */
	void deleteById(ID id);
	
	/**
	 * 
			*@name 分页查询列表
			*@Description  
			*@CreateDate 2015年8月21日下午1:32:57
	 */
	List<T> listPage(Map paramMap);

	/**
	 * 
			*@name 查询数目
			*@Description  
			*@CreateDate 2015年8月21日下午1:33:05
	 */
	long count(Map paramMap);

	/**
	 * 
			*@name 获取指定对象记录
			*@Description  
			*@CreateDate 2015年8月21日下午1:33:15
	 */
	T get(Map paramMap);
	
	/**
	 * 
			*@name 根据id获取对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:33:26
	 */
	T getById(ID id);
}
