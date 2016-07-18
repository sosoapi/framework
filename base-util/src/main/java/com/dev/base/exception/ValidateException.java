package com.dev.base.exception;

/**
 * 
		* <p>Title: 验证失败异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:03:45</p>
 */
public class ValidateException extends BaseRuntimeException{
	private static final long serialVersionUID = 1L;

	public ValidateException(String errorCode) {
		this.errorCode = errorCode;
	}

	public ValidateException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ValidateException(String errorCode, String errorMsg,Object data) {
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
