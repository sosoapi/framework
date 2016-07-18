package com.dev.base.concurrent;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dev.base.concurrent.TaskProcess;
import com.dev.base.concurrent.TaskUtils;

public class TaskUtilsTest {
	final List<User> userList = new ArrayList<User>();
	
	@Before
	public void init(){
		for (int i = 0; i < 100; i++) {
			userList.add(new User(i + "", i));
		}
	}
	
	@Test
	public void testExecAsyn() {
		long startTime = System.currentTimeMillis();
		TaskUtils.execAsyn(new Runnable() {
			@Override
			public void run() {
				for (User user : userList) {
					user.getName();
				}
			}
		});
		System.out.println("execAsyn spend:" + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void testExecMuil() {
		long startTime = System.currentTimeMillis();
		TaskUtils.exec(userList, 5, new TaskProcess<User>() {
			@Override
			public void exec(int index,User task) {
				System.out.println(Thread.currentThread().getName() + ":" + task.getName());
			}
		});
		System.out.println("execMuil spend:" + (System.currentTimeMillis() - startTime));
	}
	
	@Test
	public void testExecCommon(){
		long startTime = System.currentTimeMillis();
		for (User user : userList) {
			user.getName();
		}
		
		System.out.println("execCommon spend:" + (System.currentTimeMillis() - startTime));
	}
}
