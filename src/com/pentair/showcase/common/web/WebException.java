package com.pentair.showcase.common.web;

/**
 * Web层公用的Exception.
 * 
 * @author Jiangshilin
 */
public class WebException extends RuntimeException {

	private static final long serialVersionUID = 1401593546385403720L;

	public WebException() {
		super();
	}

	public WebException(String message) {
		super(message);
	}

	public WebException(Throwable cause) {
		super(cause);
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}
}