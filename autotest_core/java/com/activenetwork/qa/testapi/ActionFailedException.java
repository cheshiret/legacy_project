package com.activenetwork.qa.testapi;

/**
 * Thrown when an action is failed to perform.
 * 
 * @author jdu
 */

public class ActionFailedException extends AutoRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ActionFailedException with no detail message.
	 */
	public ActionFailedException() {
		super();
	}

	/**
	 * Constructs a ActionFailedException with the specified detail message.
	 * 
	 * @param message
	 *            the detail message
	 */
	public ActionFailedException(String message) {
		super(message);
	}

	/**
	 * Constructs a ActionFailedException with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public ActionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a ActionFailedException with the specified cause.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ActionFailedException(Throwable cause) {
		super(cause);
	}
}
