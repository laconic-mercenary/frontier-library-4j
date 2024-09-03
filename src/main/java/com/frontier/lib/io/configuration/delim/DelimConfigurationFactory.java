package com.frontier.lib.io.configuration.delim;

public abstract class DelimConfigurationFactory {

	public static DelimConfigurationProcessor make(
			DelimConfigurationParameters parameters) {
		return new DelimConfigurationProcessor(parameters);
	}

}
