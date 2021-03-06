package com.activenetwork.qa.testapi;

public class DuplicatedDataException extends AutoRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DuplicatedDataException() {
		super();
	}
	
	/**
	 * When Doing verification, we expect use this constructor to generate message 
	 * with expected result and actual result;
	 * @param message
	 * @param expectedResult
	 * @param actualResult
	 */
	public DuplicatedDataException(String message,Object expectedResult,Object actualResult) {
		super(message+"\n Expected Result:"+expectedResult+"\n Actual   Result:"+actualResult);
	}

	/**
	 * @param message
	 */
	public DuplicatedDataException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DuplicatedDataException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicatedDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
