package com.dev.base.concurrent;

import java.util.List;

/**
 * 
		* <p>Title: 任务处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:02:21</p>
 */
public interface TaskListProcess<T,V>{
	/**
	 * 
			*@name 处理任务列表，尽量不要有线程间共享变量，防止等待
			*@Description 相关说明 
			*@Time 创建时间:2014年12月27日上午11:02:46
	 */
	List<V> exec(int batchIndex,List<T> taskList);
}
