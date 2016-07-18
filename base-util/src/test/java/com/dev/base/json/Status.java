package com.dev.base.json;

public enum Status {
	normal("1","正常"),
	delete("2","删除");

	private Status(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/** 编码*/
	private String code;
	/** 描述*/
	private String desc;
	
	@Override
	public String toString() {
		return getCode();
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
