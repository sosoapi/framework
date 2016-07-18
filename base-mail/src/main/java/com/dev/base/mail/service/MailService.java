package com.dev.base.mail.service;

import java.util.List;

import com.dev.base.mail.entity.MailMsg;

/**
 * 
		* <p>Title: 邮件发送服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:03:18</p>
 */
public interface MailService {
	/**
	 * 
			*@name 邮件发送，默认发送者
			*@Description  
			*@CreateDate 2015年8月22日上午10:06:36
	 */
	void sendMail(String toEmail,MailMsg mailMsg);
	
	/**
	 * 
			*@name 邮件群发，默认发送者
			*@Description  
			*@CreateDate 2015年8月22日上午11:01:39
	 */
	void sendMail(List<String> toEmailList,MailMsg mailMsg);
	
	/**
	 * 
			*@name 邮件发送
			*@Description  
			*@CreateDate 2015年8月22日上午10:06:36
	 */
	void sendMail(String fromEmail,String fromPasswd,String fromName,
					String host,String toEmail,MailMsg mailMsg);
	
	/**
	 * 
			*@name 邮件群发
			*@Description  
			*@CreateDate 2015年8月22日上午11:01:39
	 */
	void sendMail(String fromEmail,String fromPasswd,String fromName,
					String host,List<String> toEmailList,MailMsg mailMsg);
}
