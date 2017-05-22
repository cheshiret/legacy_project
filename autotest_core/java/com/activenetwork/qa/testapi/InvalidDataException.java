package com.activenetwork.qa.testapi;

/**
 * Thrown when some data are invalid in format, range, or type
 * @author jdu
 *
 */

@SuppressWarnings("serial")
public class InvalidDataException extends AutoRuntimeException {

	/**
	 * 
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidDataException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidDataException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
