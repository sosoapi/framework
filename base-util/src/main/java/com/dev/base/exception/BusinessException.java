package com.dev.base.exception;

/**
 * 
		* <p>Title: 业务异常基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:03:33</p>
 */
public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;

	/** 错误码 */
	private String errorCode;

	/** 错误提示信息 */
	private String errorMsg;

	/** 额外信息 */
	private Object data;
	
	public BusinessException(){
		
	}
	
	public BusinessException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BusinessException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public BusinessException(String errorCode, String errorMsg,Object data) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public Object getData() {
		return data;
	}
}
