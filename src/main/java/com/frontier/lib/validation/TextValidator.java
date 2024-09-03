package com.frontier.lib.validation;

public class TextValidator {

	public boolean isEmpty(String text) {
		return isEmpty(text, true);
	}

	public boolean isEmpty(String text, boolean treatNullAsEmpty) {
		return isEmpty(text, treatNullAsEmpty, true);
	}

	public boolean isEmpty(String text, boolean treatNullAsEmpty,
			boolean trimWhitespace) {
		if (text == null)
			return treatNullAsEmpty;

		if (trimWhitespace)
			return (text.trim().isEmpty());

		return text.isEmpty();
	}

	public boolean isOverflow(CharSequence text, long maxLength) {
		return isOverflow(text, maxLength, 0);
	}

	public boolean isOverflow(CharSequence text, long maxLength, long minLength) {
		return isOverflow(text, maxLength, minLength, true);
	}

	public boolean isOverflow(CharSequence text, long maxLength,
			long minLength, boolean failOnNull) {
		if (text == null)
			if (!failOnNull)
				return (minLength > 0);

		return (text.length() < minLength || text.length() > maxLength);
	}

	public boolean isUpperCase(String text) {
		return text.toUpperCase().equals(text);
	}

	public boolean isLowerCase(String text) {
		return text.toLowerCase().equals(text);
	}

	public static boolean isOverflowing(CharSequence text, long maxLength) {
		return new TextValidator().isOverflow(text, maxLength);
	}

	public static boolean isEmptyStr(String text) {
		return new TextValidator().isEmpty(text);
	}

	public static void raiseIfEmptyStr(String text) {
		if (isEmptyStr(text)) {
			throw new IllegalArgumentException("String cannot be null or empty");
		}
	}
}
