/**
 * 
 */
package com.frontier.lib.threading;

/**
 * @author mlcs05
 *
 */
public abstract class ThreadUtils {

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
