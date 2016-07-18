package com.dev.base.exception;

/**
 * 
		* <p>Title: http异常</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午2:40:04</p>
 */
@SuppressWarnings("serial")
public class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }
}
