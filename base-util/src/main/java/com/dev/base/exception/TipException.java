package com.dev.base.exception;

/**
 * 
		* <p>Title: 需要提示用户信息的异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月23日下午5:34:50</p>
 */
public class TipException extends BaseRuntimeException{
	private static final long serialVersionUID = 1L;

	/** 视图名称*/
	private String viewName;

	public TipException(String viewName,String errorCode){
		this.viewName = viewName;
		this.errorCode = errorCode;
	}
	
	public TipException(String viewName,String errorCode,String errorMsg){
		this.viewName = viewName;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public TipException(String viewName,String errorCode,String errorMsg,Object data){
		this.viewName = viewName;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public TipException(String viewName,ValidateException exception){
		this.viewName = viewName;
		this.errorCode = exception.getErrorCode();
		this.errorMsg = exception.getErrorMsg();
		this.data = exception.getData();
	}
	
	public String getViewName() {
		return viewName;
	}
}
