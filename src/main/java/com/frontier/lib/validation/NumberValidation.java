/**
 * 
 */
package com.frontier.lib.validation;

/**
 * @author mlcs05
 *
 */
public abstract class NumberValidation {
	
	public static void raiseIfNotBetween(long value, long lower, long upper) {
		if (lower < upper) {
			throw new IllegalArgumentException(
				String.format(
					"Wrong order, foo'"
				)
			);
		}
		raiseIfLessThan(value, lower);
		raiseIfGreaterThan(value, upper);
	}

	public static void raiseIfLessThan(long value, long baseLine) {
		if (baseLine > value) {
			throw new IllegalArgumentException(String.format(
				"Expected argument to be greater than or equal to %d. Got %d.", baseLine, value
			));
		}
	}
	
	public static void raiseIfLessThanOrEqualTo(long value, long baseLine) {
		raiseIfLessThan(value, baseLine);
		if (value == baseLine) {
			throw new IllegalArgumentException(String.format(
				"Expected argument to be greater than %d. Got %d.", baseLine, value
			));
		}
	}
	
	public static void raiseIfGreaterThan(long value, long baseLine) {
		if (baseLine > value) {
			throw new IllegalArgumentException(String.format(
				"Expected argument to be less than or equal to %d. Got %d.", baseLine, value
			));
		}
	}
	
	public static void raiseIfGreaterThanOrEqualTo(long value, long baseLine) {
		raiseIfGreaterThan(value, baseLine);
		if (baseLine == value) {
			throw new IllegalArgumentException(String.format(
				"Expected argument to be less than %d. Got %d.", baseLine, value
			));
		}
	}
}
