package com.dev.base.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.dev.base.exception.ValidateException;
import com.dev.base.exception.errorcode.SysErrorCode;
import com.dev.base.utils.MapUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
		* <p>Title: json工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:59:44</p>
 */
public final class JsonUtils {

	/** ObjectMapper */
	private static ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//enum转换为数值
//		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);
	}
	
	private JsonUtils() {
		
	}

	/**
	 * 
			*@name 将对象转换为JSON字符串
			*@Description  
			*@CreateDate 2015年8月21日下午3:00:01
	 */
	public static String toJson(Object value) {
		try {
			//enum转换为数值
//			mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
			*@name 将对象转换为JSON字符串并格式化
			*@Description 相关说明 
			*@Time 创建时间:2015年8月5日下午1:28:00
	 */
	public static String toFormatJson(Object value) {
		try {
			//enum转换为数值
//			mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
			*@name 格式化json
			*@Description 相关说明 
			*@Time 创建时间:2015年8月5日下午1:31:04
	 */
	public static String formatJson(String json){
		Object object = toObject(json, Object.class);
		return toFormatJson(object);
	}
	
	/**
	 * 
	 * @name 统一报文协议
	 * @Description 远程调用返回值采用统一报文 
	 * 				成功: 
	 * 					errorCode:0 
	 * 					data:返回数据
	 *              失败: 
	 *              	errorCode:非0 
	 *              	errorMsg:错误提示信息 
	 *              	data:错误提示数据(可选)
	 * @Time 创建时间:2014-5-15下午3:59:23
	 */
	public static Map<String, Object> createSuccess() {
		return createSuccess(null);
	}

	/**
	 * 
	 * @name 统一报文协议
	 * @Description 远程调用返回值采用统一报文 
	 * 				成功: 
	 * 					errorCode:0 
	 * 					data:返回数据
	 *              失败: 
	 *              	errorCode:非0 
	 *              	errorMsg:错误提示信息 
	 *              	data:错误提示数据(可选)
	 * @Time 创建时间:2014-5-15下午3:59:23
	 */
	public static Map<String, Object> createSuccess(Object value) {
		Map<String, Object> result = MapUtils.newMap();
		result.put("errorCode", SysErrorCode.SUCCESS);
		if (value != null) {
			result.put("data", value); 
		}

		return result;
	}

	/**
	 * 
			*@name 将JSON字符串转换为对象
			*@Description  
			*@CreateDate 2015年8月21日下午3:00:45
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidateException(SysErrorCode.SYS_001);
		}
	}

	/**
	 * 
			*@name 将JSON字符串转换为对象
			*@Description  
			*@CreateDate 2015年8月21日下午3:00:54
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return (T)mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
			*@name 将JSON字符串转换为对象
			*@Description  
			*@CreateDate 2015年8月21日下午3:01:05
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return (T)mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
			*@name 将对象转换为JSON流
			*@Description  
			*@CreateDate 2015年8月21日下午3:01:14
	 */
	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
			*@name 序列化对象
			*@Description  
			*@CreateDate 2015年8月21日下午3:01:25
	 */
	public static byte[] serializeObject(Object o) {
		try {
			return mapper.writeValueAsBytes(o);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
			*@name 根据类型反序列化对象
			*@Description  
			*@CreateDate 2015年8月21日下午3:01:47
	 */
	public static <T> T deSerializeObject(byte[] bytes,TypeReference<T> type) {
		try {
			return (T)mapper.readValue(bytes, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Set<Long> test = new HashSet<Long>();
		test.add(1L);
		test.add(2L);
		test.add(3L);
		
		byte[] json = JsonUtils.serializeObject(test);
		
		Set<Long> test1 = JsonUtils.deSerializeObject(json,new TypeReference<Set<Long>>(){});
		
		for (Long long1 : test1) {
			System.out.println(long1);
		}
	}
}