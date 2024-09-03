package com.frontier.lib.io.configuration.delim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.frontier.lib.validation.TextValidator;

public class DelimConfigurationProcessor {

	private DelimConfigurationParameters parameters = null;

	// package-private
	DelimConfigurationProcessor(DelimConfigurationParameters parameters) {
		setParameters(parameters);
	}

	public DelimConfigurationParameters getParameters() {
		return parameters;
	}

	public void setParameters(DelimConfigurationParameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * Ensures that the parameters provided are not null, and have an item and
	 * attribute delimeter set.
	 * 
	 * @throws InvalidDelimConfigurationException
	 */
	public void assertCompleteParameters()
			throws InvalidDelimConfigurationException {
		if ((getParameters() == null)
				|| TextValidator.isEmptyStr(getParameters().getItemDelimeter())
				|| TextValidator.isEmptyStr(getParameters()
						.getAttributeDelimeter())) {
			throw new InvalidDelimConfigurationException(getParameters());
		}
	}

	/**
	 * Goes through the configuration string and separates values out based on
	 * the provided delimeters. This is probably best demonstrated with examples
	 * - see the {@link DelimConfigurationProcessorTest} unit test.
	 * 
	 * @param configurationString
	 * @return
	 */
	public List<List<String>> parseAsList(String configurationString) {
		final String itemDelim = getParameters().getItemDelimeter();
		final String attrDelim = getParameters().getAttributeDelimeter();
		List<List<String>> results = new LinkedList<>();
		String[] items = configurationString.split(itemDelim);
		for (String item : items) {
			String[] attrs = item.split(attrDelim);
			List<String> attrList = new ArrayList<>(attrs.length);
			for (String attr : attrs)
				attrList.add(attr);
			results.add(attrList);
		}
		return results;
	}

	/**
	 * First parses the string as a list, then makes the item at the specified
	 * keyIndex the key in the map - where as the rest become members of the
	 * list belonging to that key.
	 * 
	 * @param configurationString
	 *            The string to break out / parse
	 * @param keyIndex
	 *            0-based index in the list to use as a key.
	 * @return
	 */
	public Map<String, List<String>> parseAsMap(String configurationString,
			int keyIndex) {
		Map<String, List<String>> map = new LinkedHashMap<>();
		List<List<String>> list = parseAsList(configurationString);
		for (List<String> innerList : list) {
			if (keyIndex >= innerList.size()) {
				return Collections.emptyMap();
			} else {
				List<String> mapList = new LinkedList<>();
				for (int i = 0; i < innerList.size(); i++) {
					String current = innerList.get(i);
					if (i == keyIndex)
						map.put(current, mapList);
					else
						mapList.add(current);
				}
			}
		}
		return map;
	}
}
