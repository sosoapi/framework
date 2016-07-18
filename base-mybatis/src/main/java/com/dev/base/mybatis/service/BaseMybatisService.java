package com.dev.base.mybatis.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 基础服务接口</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:33:47</p>
 */
public interface BaseMybatisService<T extends BaseMybatisEntity, ID extends Serializable> {
	/**
	 * 
			*@name 新增对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:33:58
	 */
	T add(T param);

	/**
	 * 
			*@name 批量新增
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:07
	 */
	void batchAdd(List<T> batchList);
	
	/**
	 * 
			*@name 修改对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:16
	 */
	T update(T param);

	/**
	 * 
			*@name 删除对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:26
	 */
	void delete(T param);

	/**
	 * 
			*@name 根据id进行删除
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:34
	 */
	void deleteById(ID id);
	
	/**
	 * 
			*@name 分页查询列表
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:43
	 */
	List<T> listPage(int pageNumber,int pageSize,Map<String,Object> paramMap);

	/**
	 * 
			*@name 查询数目
			*@Description  
			*@CreateDate 2015年8月21日下午1:34:53
	 */
	long count(Map paramMap);

	/**
	 * 
			*@name 获取指定对象记录
			*@Description  
			*@CreateDate 2015年8月21日下午1:35:01
	 */
	T get(Map paramMap);
	
	/**
	 * 
			*@name 根据id获取对象
			*@Description  
			*@CreateDate 2015年8月21日下午1:35:09
	 */
	T getById(ID id);
}
