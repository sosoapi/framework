package com.dev.base.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
		* <p>Title: 自定义json处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:15:28</p>
 */
public class ObjectMappingCustomer extends ObjectMapper {
	private static final long serialVersionUID = -5745400612812727026L;

	public ObjectMappingCustomer() {
		super();
		//过滤值为null的属性
//		this.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		//配置方法
		/*<property name="objectMapper">
	    	<bean class="com.moshi.base.json.ObjectMappingCustomer">
	    		<property name="serializationInclusion">
	                <value type="com.fasterxml.jackson.annotation.JsonInclude.Include">NON_NULL</value>
	            </property>
	    	</bean>
	    </property>*/
		
		// 空值处理成字符串
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jg,
							SerializerProvider sp) throws IOException,
							JsonProcessingException {
						jg.writeString("");
					}
				});
	}
}
