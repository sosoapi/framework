package com.dev.base.mail.entity;

import java.net.URL;

/**
 * 
		* <p>Title: 附件</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日上午11:03:12</p>
 */
public class MailAttach {
	/** 附件名称*/
    private String name;

    /** 附件描述*/
    private String description;

    /** 附件文件路径，绝对路径*/
    private String path;

    /** 附件url*/
    private URL url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
