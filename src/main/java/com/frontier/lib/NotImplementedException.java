
package com.frontier.lib;

public class NotImplementedException extends RuntimeException {

	/**
	 * serializable
	 */
	private static final long serialVersionUID = -242342293062689081L;

	/**
	 * Constructs with default not implemented message
	 */
	public NotImplementedException() {
		super("This operation or process is not implemented yet.");
	}

	/**
	 * Constructs message with the name of the method or operation
	 * 
	 * @param methodOrOperationName
	 *           Like the name of a method for example.
	 */
	public NotImplementedException(String methodOrOperationName) {
		super("This operation or process is not implemented yet: " + methodOrOperationName);
	}
}
