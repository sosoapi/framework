package com.dev.base.exception;

/**
 * 
		* <p>Title: 基础异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:03:45</p>
 */
public class BaseRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/** 错误码 */
	protected String errorCode;

	/** 错误提示信息 */
	protected String errorMsg;

	/** 额外信息 */
	protected Object data;
	
	public BaseRuntimeException(){
		
	}
	
	public BaseRuntimeException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public BaseRuntimeException(String errorCode, String errorMsg,Object data) {
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
