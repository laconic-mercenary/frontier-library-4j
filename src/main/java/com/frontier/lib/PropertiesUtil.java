package com.frontier.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	/**
	 * Uses a class loader to read in a file under the resources directory of a
	 * project.
	 * 
	 * @param filename
	 *            The filename (non-absolute).
	 * @param threadClassLoader
	 *            Can be acquired using
	 *            <code>Thread.currentThread().getClassLoader()</code>
	 * @return A completely filled out Properties file
	 * @throws IOException
	 *             If an issue occurs in fetching the resource file
	 */
	public Properties readFromResource(String filename,
			ClassLoader threadClassLoader) throws IOException {
		Properties properties = new Properties();
		try (InputStream is = threadClassLoader.getResourceAsStream(filename)) {
			properties.load(is);
		}
		return properties;
	}

	/**
	 * @see PropertiesUtil#readFromResource(String, ClassLoader)
	 */
	public static Properties readFrom(String resourceFilename,
			ClassLoader threadClassLoader) throws IOException {
		return new PropertiesUtil().readFromResource(resourceFilename,
				threadClassLoader);
	}

}
