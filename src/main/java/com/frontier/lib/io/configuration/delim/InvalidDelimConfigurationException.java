package com.frontier.lib.io.configuration.delim;

import com.frontier.lib.validation.TextValidator;

public class InvalidDelimConfigurationException extends Exception {

	private static final long serialVersionUID = -2475130564422669600L;

	public InvalidDelimConfigurationException(
			DelimConfigurationParameters params) {
		super(determineIssue(params));
	}

	private static String determineIssue(DelimConfigurationParameters params) {
		String msg = null;
		if (params == null) {
			msg = "parameter instance wase null";
		} else {
			if (TextValidator.isEmptyStr(params.getItemDelimeter())) {
				msg = "Item delimeter was not set.";
			} else if (TextValidator.isEmptyStr(params.getAttributeDelimeter())) {
				msg = "Attribute delimeter was not set.";
			}
		}
		return msg;
	}
}
