package com.frontier.lib.validation;

import com.frontier.lib.NullParameterException;

public class ObjectValidator {

	public static void raiseIfNull(Object obj) {
		raiseIfNull(obj, null);
	}

	public static void raiseIfNull(Object obj, String paramName) {
		if (obj == null) {
			if (paramName != null)
				throw new NullParameterException(paramName);
			else
				throw new NullParameterException();
		}
	}
}
