
package com.frontier.lib;

/**
 * Represents a character sequence whose CharSequence implemented operations
 * carry a timeout assertion on them. A timeout is specified and is then checked
 * at every CharSequence operation. The operations are:
 * <ul>
 * <li>
 * <code>charAt(index)</code></li>
 * <li>
 * <code>length()</code></li>
 * <li>
 * <code>subSequence(start, end)</code></li>
 * </ul>
 * 
 * This is helpful for providing 'black-box' methods from libraries that accept
 * a CharSequence a means of ensuring that processing does not take too long
 * based on the CharSequence provided to them.
 * 
 * A good example of this is in preventing ReDoS attacks using the Java Pattern
 * and Matcher regex matching utilities.
 * 
 * @author mlcs
 */
public class InterruptibleCharSequence implements CharSequence {

	private CharSequence sequence = null;
	private Long startTime = null;
	private Long timeoutMilliseconds = null;

	/**
	 * Constructs a new character sequence. This will typically be a String.
	 * 
	 * @param sourceSequence
	 *           The immutable CharSequence object, typically a String.
	 */
	public InterruptibleCharSequence(CharSequence sourceSequence) {
		sequence = sourceSequence;
	}

	@Override
	public char charAt(int index) {
		assertTimeout();
		return sequence.charAt(index);
	}

	@Override
	public int length() {
		assertTimeout();
		return sequence.length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		assertTimeout();
		return sequence.subSequence(start, end);
	}

	/**
	 * Places a timestamp in the start time field to where the timeout is added
	 * to, and assertions can begin.
	 */
	public void startTimer() {
		startTime = retreiveCurrentTime();
	}

	/**
	 * Clears out the start time field and prevents any timeout assertions from
	 * occurring.
	 */
	public void stopTimer() {
		startTime = null;
	}

	/**
	 * Returns the timeout value as a long in milliseconds.
	 * 
	 * @return
	 */
	public Long getTimeout() {
		return timeoutMilliseconds;
	}

	/**
	 * Sets the timeout value in milliseconds to which the assertions will be
	 * made.
	 * 
	 * @param timeout
	 */
	public void setTimeout(Long timeout) {
		this.timeoutMilliseconds = timeout;
	}

	private void assertTimeout() {
		if (startTime != null) {
			final Long currentTime = retreiveCurrentTime();
			final Long assertionTime = startTime + getTimeout();
			if (assertionTime <= currentTime) {
				throw new InterruptException();
			}
		}
	}

	private Long retreiveCurrentTime() {
		return System.currentTimeMillis();
	}

	/**
	 * Represents when a timeout occurs for the InterruptibleCharSequence class.
	 * This is a runtime exception.
	 * 
	 * @author mlcs
	 */
	public static final class InterruptException extends RuntimeException {

		private static final long serialVersionUID = 7495914071480235370L;

		/**
		 * Creates a new InterruptException with the default message.
		 */
		public InterruptException() {
			super("Processing of this character sequence timed out");
		}
	}
}