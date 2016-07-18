package com.dev.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.jdbc.annotation.ReadDataSource;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.demo.dao.DemoDao;
import com.dev.demo.entity.Demo;
import com.dev.demo.service.DemoService;

/**
 * 
		* <p>Title: 标题（要求能简洁地表达出类的功能和职责）</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:38:07</p>
 */
@Service("demoServiceImpl")
public class DemoServiceImpl extends BaseMybatisServiceImpl<Demo, Long,DemoDao> implements DemoService{
	@Override
//	@Transactional
	//统一采用声明式事务
	public void batchAddForRoll(List<Demo> list) {
		for (Demo demo : list) {
			getMybatisDao().add(demo);
			
			if (list.size() > 3) {
				throw new RuntimeException("transaction test");
			}
		}
	}
	
	@ReadDataSource
	@Override
	public List<Demo> listPage(int pageNumber,int pageSize,Map<String,Object> paramMap) {
		return super.listPage(pageNumber, pageSize, paramMap);
	}
}
