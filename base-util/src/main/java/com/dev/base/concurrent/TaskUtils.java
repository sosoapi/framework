package com.dev.base.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
		* <p>Title: 多线程处理工作列表</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:02:52</p>
 */
public class TaskUtils {
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	/**
	 * 
			*@name 将工作列表通过指定线程数来处理，全部执行后才返回，一次执行一个任务
			*@Description 相关说明 
			*@Time 创建时间:2014年12月27日上午10:02:02
	 */
	public static <T,V> void exec(final List<T> workList,int threadCount,final TaskProcess<T> process){
		if (workList == null || workList.isEmpty()) {
			return ;
		}
		
		ExecutorService pool = Executors.newFixedThreadPool(threadCount); 
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(pool);
		int workSize = workList.size();
		for (int i = 0; i < workSize; i++) {
			final int index = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					process.exec(index,workList.get(index));
					return 1;
				}
			});
		}
		
		//等待全部执行完成
		for (int i = 0; i < workSize; i++) {
            try {
				completionService.take();
			} 
            catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		pool.shutdown();
	}
	
	//获取批次数
	private static int getBatchCount(int size,int per){
		if (size <= per) {
			return 1;
		}

		int temp = size / per;
		return size % per == 0 ? temp : (temp + 1);
	}
		
	/**
	 * 
			*@name 将任务列表分段执行
			*@Description 相关说明 
			*@Time 创建时间:2015年1月19日下午4:56:14
			*@param workList		任务列表
			*@param maxThreadCount	最大线程数
			*@param perBatchCount	每批次执行任务数
			*@param process			任务处理
	 */
	public static <T,V> List<V> execList(final List<T> workList,int maxThreadCount,
									int perBatchCount,final TaskListProcess<T,V> process){
		List<V> result = new ArrayList<V>();
		if (workList == null || workList.isEmpty()) {
			return (List<V>)result;
		}
		
		//总通讯录用户数
		int size = workList.size();
		//批量处理批次
		int count = getBatchCount(size,perBatchCount);
		//线程池数目
		int threadCount = maxThreadCount > count ? count : maxThreadCount;
		
		//线程数目
		ExecutorService pool = Executors.newFixedThreadPool(threadCount); 
		CompletionService<List> completionService = new ExecutorCompletionService<List>(pool);
		
		//多线程批量注册
		List<T> batchList = new ArrayList<T>();
		int batchIndex = 0;
		for (int i = 0; i < size; i++) {
			if (batchList.size() < perBatchCount) {
				batchList.add(workList.get(i));
			}
			
			//批量处理
			if (i == (size - 1) || batchList.size() == perBatchCount) {
				final List<T> tempList = batchList;
				final int tempBatchIndex = batchIndex ++;
				batchList = new ArrayList<T>();
				completionService.submit(new Callable<List>() {
					@Override
					public List call() throws Exception {
						return process.exec(tempBatchIndex,tempList);
					}
				});
			}
		}
		
		//等待全部执行完成
		for (int i = 0; i < count; i++) {
            try {
            	result.addAll(completionService.take().get());
			} 
            catch (Exception e) {
				e.printStackTrace();
			}
		}
		pool.shutdown();
				
		return result;
	}
	
	/**
	 * 
			*@name 异步执行任务
			*@Description 相关说明 
			*@Time 创建时间:2014年12月27日上午11:19:45
			*@param runnable
	 */
	public static void execAsyn(Runnable runnable){
		executorService.execute(runnable);
	}
}

