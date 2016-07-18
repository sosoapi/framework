package com.dev.base.mybatis.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import com.dev.base.mybatis.BaseMybatisEntity;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.mybatis.service.BaseMybatisService;

/**
 * 
		* <p>Title: 服务基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:35:24</p>
 */
public abstract class BaseMybatisServiceImpl<T extends BaseMybatisEntity, ID extends Serializable,X extends BaseMybatisDao<T, ID>> 
					implements BaseMybatisService<T, ID>,ApplicationContextAware {
	//相当于注入BaseMybatisDao
	//导致多个实现类，异常
	//@Autowired
	private X mybatisDao;

	/** 实体类类型 */
	private Class<X> daoClass;
	
	/** spring上下文 */
	private ApplicationContext context;
	
	/** 默认批量插入数目*/
	private int batchCount = 50;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public BaseMybatisServiceImpl() {
		Type type = getClass().getGenericSuperclass();
		if (ParameterizedType.class.isAssignableFrom(type.getClass())){
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			daoClass = (Class<X>) parameterizedType[2];
		}
	}
	
	@PostConstruct
	private void initDao(){
		//获取实际的dao对象
		mybatisDao = context.getBean(daoClass);
	}
	
	public X getMybatisDao(){
		return mybatisDao;
	}
	
	@Override
	public T add(T param) {
		getMybatisDao().add(param);
		
		return param;
	}
	
	@Override
	public void batchAdd(List<T> paramList) {
		if (paramList.isEmpty()) {
			return ;
		}
		
		int size = paramList.size();
		List<T> batchList = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			if (batchList.size() < batchCount) {// 当前还未到批量插入数目，则先保存
				batchList.add(paramList.get(i));
			}
			
			// 如果是最后一批记录或已经满足插入条件，则执行批量插入
			if (i == (size - 1) || batchList.size() == batchCount) {
				getMybatisDao().batchAdd(batchList);
				// 清空，为下一批做准备
				batchList.clear();
			}
		}
	}

	@Override
	public T update(T param) {
		getMybatisDao().update(param);
		
		return param;
	}

	@Override
	public void delete(T param) {
		getMybatisDao().delete(param);
	}

	@Override
	public void deleteById(ID id){
		getMybatisDao().deleteById(id);
	}
	
	@Override
	public List<T> listPage(int pageNumber,int pageSize,Map<String,Object> paramMap) {
		int firstResult = (pageNumber - 1) * pageSize;
		if (paramMap == null) {
			paramMap = new HashMap<String,Object>();
		}
		paramMap.put(BaseMybatisDao.KEY_PAGE_START, firstResult);
		paramMap.put(BaseMybatisDao.KEY_PAGE_SIZE, pageSize);
		
		return getMybatisDao().listPage(paramMap);
	}

	@Override
	public long count(Map paramMap) {
		return  getMybatisDao().count(paramMap);
	}

	@Override
	public T get(Map paramMap) {
		return getMybatisDao().get(paramMap);
	}

	@Override
	public T getById(ID id) {
		return getMybatisDao().getById(id);
	}

	public int getBatchCount() {
		return batchCount;
	}

	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}
	
	public String getLikeExpr(String value){
		return StringUtils.isEmpty(value) ? value : new StringBuilder("%").append(value).append("%").toString();
	}
}
