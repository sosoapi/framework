package com.dev.base.utils.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

public class BeanTest {
	@Test
	public void testBeanProperty() throws Exception, InvocationTargetException, NoSuchMethodException {
		User user = new User();
		user.setName("test user");
		user.setAge(10);
		
		Addr addr = new Addr();
		addr.setCity("xia men");
		addr.setArea("si min");
		user.setAddr(addr);
		
		System.out.println(PropertyUtils.getNestedProperty(user, "addr.area"));
	}
	
	@Test
	public void testMapProperty() throws Exception, InvocationTargetException, NoSuchMethodException {
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("name", "test user");
		user.put("age",10);
		
		Map<String,Object> addr = new HashMap<String, Object>();
		addr.put("city", "xia men");
		addr.put("area", "si min");
		user.put("addr", addr);
		
		System.out.println(PropertyUtils.getNestedProperty(user, "age"));
		System.out.println(PropertyUtils.getNestedProperty(user, "addr.city"));
	}
}
