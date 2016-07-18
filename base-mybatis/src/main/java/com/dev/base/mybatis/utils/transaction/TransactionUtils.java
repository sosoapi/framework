package com.dev.base.mybatis.utils.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 
		* <p>Title: 编程式事务处理工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:36:30</p>
 */
public class TransactionUtils{
	private static PlatformTransactionManager transactionManager;
	
	private static DefaultTransactionDefinition definition = getDefaultTransactionDefinition();
	
	/**
	 * 编程式事务处理
	 * 将需要事务处理的业务放在ITransaciton接口方法exec即可
	 * @param transaction	业务处理接口
	 * @throws Exception		异常
	 */
	public static void doWithTransaction(ITransaction transaction) {
		doWithTransaction(transaction, definition);
	}
	
	/**
	 * 编程式事务处理
	 * 将需要事务处理的业务放在ITransaciton接口方法exec即可
	 * @param transaction	业务处理接口
	 * @param isolationLevel	事务隔离级别，TransactionDefinition类中ISOLATION开头属性
	 * @param propagationBehavior	事务传播行为，TransactionDefinition类中PROPAGATION开头属性
	 * @throws Exception		异常
	 */
	public static void doWithTransaction(ITransaction transaction,
										int isolationLevel,int propagationBehavior ) {
		TransactionDefinition definition = getCustomTransactionDefinition(isolationLevel, propagationBehavior);
		doWithTransaction(transaction, definition);
	}
	
	private static void doWithTransaction(ITransaction transaction,TransactionDefinition definition){
		TransactionStatus status = transactionManager.getTransaction(definition);   
	    try {
	    	transaction.exec();
	    	transactionManager.commit(status);   
	    } catch (Exception e) {   
	    	transactionManager.rollback(status);   
	    	e.printStackTrace();
	    	throw new RuntimeException("transaction exec error!");
	    }   
	}
	
	private static DefaultTransactionDefinition getDefaultTransactionDefinition(){
	    return getCustomTransactionDefinition(TransactionDefinition.ISOLATION_READ_COMMITTED,
	    		TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	private static DefaultTransactionDefinition getCustomTransactionDefinition(int IsolationLevel,int propagationBehavior){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();   
	    def.setIsolationLevel(IsolationLevel);   
	    def.setPropagationBehavior(propagationBehavior);   
	    
	    return def;
	}

	public static PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public static void setTransactionManager(PlatformTransactionManager transactionManager) {
		TransactionUtils.transactionManager = transactionManager;
	}
}