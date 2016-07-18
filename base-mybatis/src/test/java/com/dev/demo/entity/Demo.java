package com.dev.demo.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 测试实体类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:37:30</p>
 */
public class Demo extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;

	public String name;

	public String firstName;
	
	public String lastName;
	
	public Integer age;
	
	public Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
