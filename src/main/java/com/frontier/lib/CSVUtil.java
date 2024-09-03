package com.frontier.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

	public static final String CONFIG_COMMENT = "#";

	public static List<String> parseResourceFile(String filepath, ClassLoader loader) throws IOException {
		List<String> lines = new ArrayList<>();
		ClassLoader cl = loader;

		try (InputStream is = cl.getResourceAsStream(filepath)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = null;

			while ((line = in.readLine()) != null) {
				if (!line.trim().startsWith(CONFIG_COMMENT)) {
					lines.add(line);
				}
			}
		}
		return lines;
	}
}
