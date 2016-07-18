package com.dev.base.mail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.dev.base.mail.common.MailCfg;
import com.dev.base.mail.entity.MailAttach;
import com.dev.base.mail.entity.MailMsg;
import com.dev.base.mail.service.MailService;

/**
 * 
		* <p>Title: 采用apache-commons-mail发送</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:07:16</p>
 */
public class ApacheMailServiceImpl implements MailService{

	@Override
	public void sendMail(String toEmail, MailMsg mailMsg) {
		sendMail(MailCfg.DEFAULT_FROM_EMAIL,MailCfg.DEFAULT_FROM_PASSWD,
					MailCfg.DEFAULT_FROM_NAME,MailCfg.HOST,toEmail, mailMsg);
	}

	@Override
	public void sendMail(List<String> toEmailList, MailMsg mailMsg) {
		sendMail(MailCfg.DEFAULT_FROM_EMAIL,MailCfg.DEFAULT_FROM_PASSWD,
					MailCfg.DEFAULT_FROM_NAME,MailCfg.HOST,toEmailList,mailMsg);
	}

	@Override
	public void sendMail(String fromEmail, String fromPasswd,String fromName,
							String host,String toEmail, MailMsg mailMsg) {
		List<String> toEmailList = new ArrayList<String>();
		toEmailList.add(toEmail);
		sendMail(fromEmail, fromPasswd,fromName,host,toEmailList, mailMsg);
	}

	@Override
	public void sendMail(String fromEmail, String fromPasswd,String fromName,
							String host,List<String> toEmailList,MailMsg mailMsg) {
		switch (mailMsg.getType()) {
			case text:
				sendTextMail(fromEmail, fromPasswd, fromName,host, toEmailList, mailMsg);
				break;
			
			case html:
				sendHtmlMail(fromEmail, fromPasswd, fromName,host, toEmailList, mailMsg);
				break;
			
			case multi:
				sendMultiMail(fromEmail, fromPasswd, fromName,host, toEmailList, mailMsg);
				break;
				
			default:
				break;
		}
	}
	
	
	//发送文本邮件
	private void sendTextMail(String fromEmail, String fromPasswd,String fromName,
								String host,List<String> toEmailList,MailMsg mailMsg){
		SimpleEmail email = new SimpleEmail();
	    try {
	    	initEmail(email, fromEmail, fromPasswd,fromName, host, toEmailList, mailMsg);
			email.setMsg(mailMsg.getContent());
	    	email.send();
	    } 
	    catch (EmailException e) {
	    	e.printStackTrace();
	    }
	}
	
	//发送html邮件
	private void sendHtmlMail(String fromEmail, String fromPasswd,String fromName,
			String host,List<String> toEmailList,MailMsg mailMsg){
		HtmlEmail email = new HtmlEmail();
		try {
	    	initEmail(email, fromEmail, fromPasswd, fromName,host, toEmailList, mailMsg);
			email.setHtmlMsg(mailMsg.getContent());
	    	email.send();
	    } 
	    catch (EmailException e) {
	    	e.printStackTrace();
	    }
	}
	
	//发送复合邮件
	private void sendMultiMail(String fromEmail, String fromPasswd,String fromName,
			String host,List<String> toEmailList,MailMsg mailMsg){
		HtmlEmail email = new HtmlEmail();
		
	    try {
	    	initEmail(email, fromEmail, fromPasswd,fromName, host, toEmailList, mailMsg);
	    	email.setHtmlMsg(mailMsg.getContent());
	    	
	    	//添加附件
	    	List<MailAttach> attachList = mailMsg.getAttachList();
	    	EmailAttachment attachment = null;
	    	for (MailAttach mailAttach : attachList) {
	    		attachment = new EmailAttachment();
	    		attachment.setDisposition(EmailAttachment.ATTACHMENT);
	    		attachment.setName(mailAttach.getName());
	    		attachment.setDescription(mailAttach.getDescription());
	    		attachment.setPath(mailAttach.getPath());
	    		attachment.setURL(mailAttach.getUrl());
	    		
	    		email.attach(attachment);
			}
	    	
	    	email.send();
	    } 
	    catch (EmailException e) {
	    	e.printStackTrace();
	    }
	}
	
	//初始化email发送信息
	private void initEmail(Email email,String fromEmail, String fromPasswd,String fromName,
							String host,List<String> toEmailList,MailMsg mailMsg) throws EmailException{
		email.setHostName(host);
	    //邮件服务器验证：用户名/密码
	    email.setAuthentication(fromEmail, fromPasswd);
	    //必须放在前面，否则乱码
	    email.setCharset(MailCfg.CHARSET);
	    email.setDebug(false);//是否开启调试默认不开启  
        email.setSSLOnConnect(true);//开启SSL加密  
        email.setStartTLSEnabled(true);//开启TLS加密 
        
        email.addTo(toEmailList.toArray(new String[0]));
    	email.setFrom(fromEmail,fromName);
    	email.setSubject(mailMsg.getSubject());
	}
}
