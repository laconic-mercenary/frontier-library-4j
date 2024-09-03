package com.frontier.lib;

/**
 * Represents an exception that is raised when a parameter is passed in that
 * ends up being null. The intent is more specific than an
 * InvalidArgumentException.
 * 
 * @author mlcs
 */
public final class NullParameterException extends RuntimeException {

	/**
	 * sid
	 */
	private static final long serialVersionUID = 7267969985306574980L;

	/**
	 * Constructs a new NullParameterException specifying the parameter name.
	 * 
	 * @param parameterName
	 *            The formal parameter's name inside the method.
	 */
	public NullParameterException(String parameterName) {
		super(String.format("Parameter '%s' cannot be null", parameterName));
	}

	/**
	 * Constructs a new NullParameterException specifying no parameter name.
	 */
	public NullParameterException() {
		super("Parameter cannot be null");
	}
}
