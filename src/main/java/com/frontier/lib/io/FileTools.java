
package com.frontier.lib.io;

import java.io.File;

/**
 * Provides a set of utilities for operating on File objects that otherwise
 * would not be provided by the language itself.
 * 
 * @author mlcs
 */
public class FileTools {

	/**
	 * Extracts the extension from a file name, some examples:
	 * 
	 * <pre>
	 * 'file.ext' = 'ext'
	 * 'file.' = '' 
	 * 'file' = ''
	 * if (!getFirstExtension)
	 *   'file.ext.ext2' = 'ext2'
	 * else
	 *   'file.ext.ext2' = 'ext.ext2'
	 * </pre>
	 * 
	 * @param file
	 *           The file object in question, it does not need to exist on disk
	 *           it must simply have a name.
	 * @param getFirstExtension
	 *           Determines how to handle the first occurrence of the file
	 *           extension delimeter, if this is set to true, then a file named
	 *           somefile.ext.ext2 will have ext.ext2 be the determined
	 *           extension, otherwise ex2 will be returned.
	 * @return Please see comments
	 */
	public String findExtension(File file, boolean getFirstExtension) {
		if (file == null)
			throw new NullPointerException("'file' parameter cannot be null");

		String ext = "";
		final String EXT_DELIM = ".";
		final String filename = file.getName();
		final Integer index = !getFirstExtension ? filename.lastIndexOf(EXT_DELIM) : filename
				.indexOf(EXT_DELIM);
		if (filename.contains(EXT_DELIM))
			if (!filename.endsWith(EXT_DELIM))
				ext = filename.substring(index + 1);
		return ext;
	}

	/**
	 * Extracts the extension from a file name, some examples:
	 * 
	 * <pre>
	 * 'file.ext' = 'ext'
	 * 'file.' = '' 
	 * 'file' = ''
	 * 'file.ext.ext2' = 'ext2'
	 * </pre>
	 * 
	 * @param file
	 * @return
	 */
	public String findExtension(File file) {
		return findExtension(file, false);
	}

	/**
	 * Iterates through all roots of the disk and obtains the total freek disk
	 * space. This is the same as what
	 * 
	 * <pre>
	 * File.getFreeSpace()
	 * </pre>
	 * 
	 * returns only this is a sum for all roots.
	 * 
	 * @return The total amount of freek diskspace in bytes. If an error
	 *         occurred, -1L is returned.
	 */
	public Long retreiveTotalFreeDiskSpace() {
		Long diskspace = 0L;
		final File[] roots = File.listRoots();
		if (roots == null)
			diskspace = -1L;
		else {
			for (File file : roots) {
				diskspace += file.getFreeSpace();
			}
		}
		return diskspace;
	}
}
