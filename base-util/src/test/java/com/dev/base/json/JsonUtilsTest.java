package com.dev.base.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;

public class JsonUtilsTest {

	@Test
	public void test() {
		System.out.println(Status.normal);
		
		System.out.println(JsonUtils.toJson(Status.normal));
	}
	
	@Test
	public void testMap(){
		Map<String, Object> value = MapUtils.newMap();
		value.put("title", "title test");
		value.put("desc", "desc test");
		value.put("imgUrl", "imgUrl test");
		value.put("redirectUrl", "redirectUrl test");
		
		System.out.println(JsonUtils.toJson(value));
	}
	
	@Test
	public void testList(){
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
		
		System.out.println(JsonUtils.toJson(list));
	}
	
	@Test
	public void testBoolean(){
		UserDto dto = new UserDto();
		dto.setAge(120);
		dto.setStudentFlag(true);
		
		System.out.println(JsonUtils.toJson(dto));
		
		String content = "{\"age\":120,\"studentFlag\":1}";
		UserDto temp = JsonUtils.toObject(content, UserDto.class);
		System.out.println(temp.isStudentFlag());
	}
}

class UserDto{
	private int age;
	private boolean studentFlag;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isStudentFlag() {
		return studentFlag;
	}
	
	public void setStudentFlag(boolean studentFlag) {
		this.studentFlag = studentFlag;
	}
}