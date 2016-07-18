package com.dev.base.exception.errorcode;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
		* <p>Title: 错误码配置</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:08:43</p>
 */
public class ErrorCodePropertyConfigurer implements InitializingBean {
	private Resource[] locations;

	public ErrorCodePropertyConfigurer() {
	}

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.build();
	}

	protected void build() {
		for (Resource location : this.locations) {
			if (location == null) {
				continue;
			}
			try {
				Properties prop = PropertiesLoaderUtils.loadProperties(new EncodedResource(location,"UTF-8"));
				for (Entry<Object, Object> entry : prop.entrySet()) {
					ErrorCodeTool.setProperty(entry.getKey().toString(), entry.getValue().toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
