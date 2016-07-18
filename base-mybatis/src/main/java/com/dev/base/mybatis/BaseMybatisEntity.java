package com.dev.base.mybatis;

import java.io.Serializable;
import java.util.Date;

/**
 * 
		* <p>Title: 实体基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午1:25:28</p>
 */
public class BaseMybatisEntity implements Serializable{
		
	private static final long serialVersionUID = 1L;

	public Long id;
	
	public Date createDate = new Date();
	
	public Date modifyDate = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
