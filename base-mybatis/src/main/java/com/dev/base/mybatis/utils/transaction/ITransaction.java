package com.dev.base.mybatis.utils.transaction;

/**
 * 
		* <p>Title: 定义事务处理接口</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:35:44</p>
 */
public interface ITransaction {
	/**
	 * 
			*@name 业务执行
			*@Description  将需要事务处理的逻辑在exec中实现,如果有异常则自动回滚
			*@CreateDate 2015年8月21日下午1:35:55
	 */
	void exec() throws Exception;
}
