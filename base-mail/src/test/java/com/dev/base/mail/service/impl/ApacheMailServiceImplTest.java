package com.dev.base.mail.service.impl;

import org.junit.Test;

import com.dev.base.mail.entity.MailAttach;
import com.dev.base.mail.entity.MailMsg;
import com.dev.base.mail.enums.MailMsgType;
import com.dev.base.mail.service.MailService;

public class ApacheMailServiceImplTest {

	@Test
	public void testSendMail() {
		MailMsg mailMsg = new MailMsg();
		mailMsg.setContent("<a href='baidu.com'>test</a>");
		mailMsg.setSubject("apache mail test");
		mailMsg.setType(MailMsgType.text);
		
		for (int i = 0; i < 2; i++) {
			MailAttach attach = new MailAttach();
			attach.setName("test " + i);
			attach.setPath("E:\\qq图片\\pig.jpg");
			
			mailMsg.getAttachList().add(attach);
		}
		
		MailService mailService = new ApacheMailServiceImpl();
		mailService.sendMail("632526474@qq.com", mailMsg);
	}
}
