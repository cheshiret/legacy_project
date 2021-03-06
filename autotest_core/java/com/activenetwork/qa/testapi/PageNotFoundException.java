package com.activenetwork.qa.testapi;

/**
 * Thrown when methods are called on a page that is not currently displayed in the browser.
 * 
 * @author ssherwood
 */
@SuppressWarnings("serial")
public class PageNotFoundException extends AutoRuntimeException {

	/**
	 * Constructs a PageNotFoundException with no detail message.
	 */
	public PageNotFoundException() {
		super();
	}

	/**
	 * Constructs a PageNotFoundException with the specified detail message.
	 * 
	 * @param message
	 *            the detail message
	 */
	public PageNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a PageNotFoundException with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a PageNotFoundException with the specified cause.
	 * 
	 * @param cause
	 *            the cause
	 */
	public PageNotFoundException(Throwable cause) {
		super(cause);
	}
}
