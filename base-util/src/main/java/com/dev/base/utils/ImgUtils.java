package com.dev.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 
		* <p>Title: 图片工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:51:12</p>
 */
public class ImgUtils {
	/**
	 * 图片默认宽度
	 */
	public final static int DEFAULT_WIDTH = 100;
	
	/**
	 * 图片默认长度
	 */
	public final static int DEFAULT_LENGTH = 100;
	
	/**
	 * 默认缩放比例
	 */
	public final static float DEFAULT_SCALE = 0.25f;
	
	/**
	 * 
			*@name 根据指定长度和宽度生成缩略图，维持原宽高比例
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午9:30:11
	 */
	public static byte[] thumbBySizeWithScale(byte[] content,int width,int length){
		InputStream inputStream = new ByteArrayInputStream(content);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Thumbnails
					.of(inputStream)
					.size(width,length)
					.toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}
	
	/**
	 * 
			*@name 根据指定长度和宽度生成缩略图，维持原宽高比例
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午9:30:11
	 */
	public static byte[] thumbBySizeWithScale(String filePath,int width,int length){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Thumbnails
					.of(filePath)
					.size(width,length)
					.toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}
	
	/**
	 * 
			*@name 根据指定长度和宽度生成缩略图，不维持原宽高比例
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午9:30:11
	 */
	public static byte[] thumbBySizeWithoutScale(byte[] content,int width,int length){
		InputStream inputStream = new ByteArrayInputStream(content);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Thumbnails
					.of(inputStream)
					.size(width,length)
					.keepAspectRatio(false)
					.toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}
	
	/**
	 * 
			*@name 按照比例进行缩放
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午9:30:11
	 */
	public static byte[] thumbByScale(byte[] content,double scale){
		InputStream inputStream = new ByteArrayInputStream(content);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Thumbnails
					.of(inputStream)
					.scale(scale)
					.toOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toByteArray();
	}
}
