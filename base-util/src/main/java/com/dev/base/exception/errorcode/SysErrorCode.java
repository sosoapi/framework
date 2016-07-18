package com.dev.base.exception.errorcode;

/**
 * 
		* <p>Title: 系统异常码常量类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:09:23</p>
 */
public interface SysErrorCode {
	//错误码命名规则:5【系统编号(0-9)】【模块编号(0-9)】【错误码(0-9)】【错误码(0-9)】
	
	/**
	 * 处理成功
	 */
	public static final String SUCCESS = "0";
	
	/**
	 * 系统繁忙
	 */
	public static final String BUSY = "-1";
	
	/**
	 * 验证失败
	 */
	public static final String SYS_001 = "50001";
	
	/**
	 * 不合法的凭证类型
	 */
	public static final String SYS_002 = "50002";
	
	/**
	 * 不合法的媒体文件类型
	 */
	public static final String SYS_003 = "50003";
	
	/**
	 * 不合法的文件类型
	 */
	public static final String SYS_004 = "50004";
	
	/**
	 * 图片上传失败
	 */
	public static final String SYS_005 = "50005";
	
	/**
	 * 无当前操作权限
	 */
	public static final String SYS_006 = "50006";
	
	/**
	 * 操作对象不存在
	 */
	public static final String SYS_007 = "50007";
	
	/**
	 * 请求方式错误
	 */
	public static final String SYS_008 = "50008";
	
	/**
	 * 加密失败
	 */
	public static final String SYS_009 = "50009";
	
	/**
	 * 解密失败
	 */
	public static final String SYS_010 = "50010";
	
	/**
	 * 登陆超时
	 */
	public static final String SYS_011 = "50011";
}
