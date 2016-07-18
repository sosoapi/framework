package com.dev.base.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
		* <p>Title: 随机数生成工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:49:16</p>
 */
public class RandomUtils {

	/** 默认范围值 */
	public static final int DEFAULT_NUMBER = 1000000;

	/**
	 * 
	 * @name 中文名称:生成指定范围内的随机数
	 * @Description 相关说明 :生成指定范围内的随机数
	 * @Time 创建时间:2014-6-18下午1:32:44
	 */
	public static int genRandomNum(int num) {
		int number = new Random().nextInt(num) + 1;
		return number;
	}

	/**
	 * 
			*@name 随机生成指定数目的指定范围不重复的随机数列表
			*@Description 生成的随机数包括0
			*			genRandomNumList(50,4)表示随机生成4个[0,50)数字
			*@Time 创建时间:2014-7-3下午6:18:42
	 */
	public static List<Integer> genRandomNumList(int num,int count){
		if (num < count) {
			return Collections.EMPTY_LIST;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		Map<Integer,String> containMap = new HashMap<Integer, String>();
		Integer temp = null;
		Random random = new Random();
		for (int i = 0; i < count; ) {
			temp = random.nextInt(num);
			if (!containMap.containsKey(temp)) {
				containMap.put(temp, null);
				result.add(temp);
				i ++;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @name 中文名称:生成默认范围内的随机数
	 * @Description 相关说明 :生成默认范围内的随机数
	 * @Time 创建时间:2014-6-18下午1:32:44
	 */
	public static int genRandomNum() {
		int number = new Random().nextInt(DEFAULT_NUMBER) + 1;
		return number;
	}

	/**
	 * 
			*@name 根据总数和每页显示条数随机页数
			*@Description 相关说明 
			*@Time 创建时间:2014-7-3下午5:17:54
	 */
	public static int getRandomPage(long count,int pageSize){
		if (count == 0 || pageSize == 0
				|| count <= pageSize) {
			return 1;
		}
		
		int pageNumber = (int)((count % pageSize == 0) 
									? count/pageSize : (count/pageSize + 1));
			
		return genRandomNum(pageNumber);
	}
	
	/**
	 * 
			*@name 上传指定范围的随机数
			*@Description 相关说明 
			*@Time 创建时间:2015年2月10日下午4:26:00
	 */
	public static int genRandomNum(int start,int end){
		return new Random().nextInt(end - start) + start;
	}
	
	public static void main(String[] args) {
		int num = 1000;
		for (int i = 0; i < 50; i++) {
			System.out.println("指定范围内：" + genRandomNum(8,15));
		}
	}
}
