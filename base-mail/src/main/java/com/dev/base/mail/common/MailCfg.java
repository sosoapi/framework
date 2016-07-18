package com.dev.base.mail.common;

import java.util.Properties;

import com.dev.base.utils.PropertiesUtils;

/**
 * 
		* <p>Title: 邮件配置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午10:27:04</p>
 */
public class MailCfg {
	//邮件配置信息
	private final static Properties mailCfg= PropertiesUtils.getProperties("mail-cfg.properties");

	/**
	 * 邮件服务器
	 */
	public final static String HOST = mailCfg.getProperty("host");
	
	/**
	 * 邮件编码
	 */
	public final static String CHARSET = mailCfg.getProperty("charset");
	
	/**
	 * 发送者邮箱
	 */
	public final static String DEFAULT_FROM_EMAIL = mailCfg.getProperty("default.from.email");
	
	/**
	 * 发送者密码
	 */
	public final static String DEFAULT_FROM_PASSWD = mailCfg.getProperty("default.from.passwd");
	
	/**
	 * 发送者名称
	 */
	public final static String DEFAULT_FROM_NAME = mailCfg.getProperty("default.from.name");
}
