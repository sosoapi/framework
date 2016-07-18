package com.dev.base.mail.entity;

import java.util.ArrayList;
import java.util.List;

import com.dev.base.mail.enums.MailMsgType;

/**
 * 
		* <p>Title: 邮件信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:02:56</p>
 */
public class MailMsg {
	/** 标题*/
	private String subject;
	
	/** 内容*/
	private String content;
	
	/** 邮件类型*/
	private MailMsgType type;
	
	/** 附件列表*/
	List<MailAttach> attachList = new ArrayList<MailAttach>();
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MailMsgType getType() {
		return type;
	}

	public void setType(MailMsgType type) {
		this.type = type;
	}

	public List<MailAttach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<MailAttach> attachList) {
		this.attachList = attachList;
	}
}
