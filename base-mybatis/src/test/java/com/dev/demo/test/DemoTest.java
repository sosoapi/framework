package com.dev.demo.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev.base.mybatis.utils.transaction.ITransaction;
import com.dev.base.mybatis.utils.transaction.TransactionUtils;
import com.dev.demo.dao.DemoDao;
import com.dev.demo.entity.Demo;
import com.dev.demo.entity.Status;
import com.dev.demo.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations="classpath:spring-mybatis-simple.xml")*/
@ContextConfiguration(locations="classpath:spring-mybatis-complex.xml")
public class DemoTest {
	@Resource(name = "demoServiceImpl")
	private DemoService demoService;
	
	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private TransactionUtils transactionUtils;
	
	@Test
	public void testList() {
		List<Demo>  result = demoService.listPage(1,10,null);
		System.out.println(result.size());
	}

	@Test
	public void testFind() {
		Demo demo = demoDao.find("name", 1);
		if (demo == null) {
			System.out.println("not find");
		}
		else {
			System.out.println("find");
		}
	}
	
	@Test
	public void testTransaction(){
		List<Demo> demoList = new ArrayList<Demo>();
		for (int i = 0; i < 5; i++) {
			Demo demo = new Demo();
			demo.setName("add " + i);
			demo.setCreateDate(new Date());
			
			demoList.add(demo);
		}
		
		demoService.batchAdd(demoList);
	}
	
	@Test
	public void testAdd(){
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 500; i++) {
			Demo demo = new Demo();
			demo.setName("add " + i);
			demo.setCreateDate(new Date());
			demo.setStatus(Status.normal);
			demoService.add(demo);
		}
		
		System.out.println("add spend:" + (System.currentTimeMillis() - startTime));
	}
	
	@Test
	public void testBatchAdd(){
		long startTime = System.currentTimeMillis();
		List<Demo> demoList = new ArrayList<Demo>();
		for (int i = 0; i < 500; i++) {
			Demo demo = new Demo();
			demo.setName("add " + i);
			demo.setCreateDate(new Date());
			demo.setStatus(Status.normal);
			demoList.add(demo);
		}
		
		demoService.batchAdd(demoList);
		
		System.out.println("add spend:" + (System.currentTimeMillis() - startTime));
	}
	
	@Test
	public void testAddByTransaction(){
		long startTime = System.currentTimeMillis();
		TransactionUtils.doWithTransaction(new ITransaction() {
			@Override
			public void exec() {
				for (int i = 0; i < 500; i++) {
					Demo demo = new Demo();
					demo.setName("add " + i);
					demo.setCreateDate(new Date());
					
					demoService.add(demo);
//					if (i == 3) {
//						throw new RuntimeException();
//					}
				}
			}
		});
		System.out.println("add spend:" + (System.currentTimeMillis() - startTime));
	}
	
	@Test
	public void testForeach() {
		List<Map> list = new ArrayList<Map>();
		Map<String,Object> value1 = new HashMap();
		value1.put("1", 1);
		value1.put("2", 1);
		
		Map<String,Object> value2 = new HashMap<String,Object>();
		value2.put("id", 2);
		value2.put("version", 2);
		
		list.add(value1);
		list.add(value2);
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idInfoList", list);
		paramMap.put("value1", value1);
		
		List<Demo>  result = demoDao.foreach(paramMap);
		System.out.println(result.size());
	}
}
