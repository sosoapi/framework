package com.dev.base.exception;

import com.dev.base.exception.errorcode.SysErrorCode;

/**
 * 
		* <p>Title: 无权限异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日上午11:17:04</p>
 */
public class AuthException extends BaseRuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthException(){
		this.errorCode = SysErrorCode.SYS_006;
	}
	
	public AuthException(String errorMsg){
		this.errorCode = SysErrorCode.SYS_006;
		this.errorMsg = errorMsg;
	}
}
