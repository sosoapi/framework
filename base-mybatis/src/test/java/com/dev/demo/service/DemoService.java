package com.dev.demo.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.demo.entity.Demo;

/**
 * 
		* <p>Title: 标题（要求能简洁地表达出类的功能和职责）</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:37:50</p>
 */
public interface DemoService extends BaseMybatisService<Demo, Long>{
	/**
	 * 批量添加
	 */
	void batchAddForRoll(List<Demo> list);
}
